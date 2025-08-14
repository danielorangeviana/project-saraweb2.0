package com.br.saraweb20.service;

import java.util.List;
import java.util.Optional;

import com.br.saraweb20.mappers.UserMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.saraweb20.dto.UserDTO;
import com.br.saraweb20.dto.UserInsertDTO;
import com.br.saraweb20.dto.UserUpdateDTO;
import com.br.saraweb20.entities.Role;
import com.br.saraweb20.entities.User;
import com.br.saraweb20.projections.UserDetailsProjection;
import com.br.saraweb20.repositories.UserRepository;
import com.br.saraweb20.exceptions.DatabaseException;
import com.br.saraweb20.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthService authService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
        this.userMapper = userMapper;
    }
	
	@Transactional(readOnly = true)
	public Page<UserDTO> findAllPaged(Pageable pageable) {
		return userRepository.findAll(pageable).map(userMapper::toDTO);
	}

	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> obj = userRepository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("User not found!"));
		return userMapper.toDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public UserDTO findLoggedUser() {
		User entity = authService.authenticated();
		return userMapper.toDTO(entity);
	}

	@Transactional
	public UserDTO insert(UserInsertDTO dto) {
        User entity = userMapper.toEntity(dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
		User savedEntity = userRepository.save(entity);
		return userMapper.toDTO(savedEntity);
	}

	@Transactional
	public UserDTO update(Long id, UserUpdateDTO dto) {
		if(!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("User not found!");
		}
		try {
			User entity = userRepository.getReferenceById(id);
			userMapper.updateEntityFromDTO(dto, entity);
			entity = userRepository.save(entity);
			return userMapper.toDTO(entity);
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("User not found!");
		}		
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("User not found!");
		}
		try {
            userRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException exception) {
			throw new DatabaseException(exception.getMessage());
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<UserDetailsProjection> result = userRepository.searchUserAndRolesByCpf(username);
		
		if(result.isEmpty()) throw new UsernameNotFoundException("User not found!");
		
		User user = new User();
		user.setCpf(username);
		user.setPassword(result.get(0).getPassword());
		result.forEach(projection -> 
	    user.addRole(new Role(projection.getRoleId(), projection.getAuthority())));
	    	
		return user;
	}
}
