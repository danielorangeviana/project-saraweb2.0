package com.br.saraweb.service;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.br.saraweb.dto.UserDTO;
import com.br.saraweb.dto.UserInsertDTO;
import com.br.saraweb.dto.UserUpdateDTO;
import com.br.saraweb.entities.User;
import com.br.saraweb.repositories.UserRepository;
import com.br.saraweb.exceptions.DatabaseException;
import com.br.saraweb.exceptions.ResourceNotFoundException;
import com.br.saraweb.mappers.UserMapper;

import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

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

	@Transactional
	public UserDTO insert(UserInsertDTO dto) {
		User entity = userMapper.toEntity(dto);
		entity.setPassword(dto.getPassword());
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
}