package com.br.saraweb.service;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.saraweb.dto.CelebrantDTO;
import com.br.saraweb.entities.Celebrant;
import com.br.saraweb.repositories.CelebrantRepository;
import com.br.saraweb.exceptions.DatabaseException;
import com.br.saraweb.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CelebrantService {

	private final CelebrantRepository repository;
	
	@Transactional(readOnly = true)
	public Page<CelebrantDTO> findAllPaged(Pageable pageable) {
		Page<Celebrant> list = repository.findAll(pageable);
		return list.map(x -> new CelebrantDTO(x));
	}
	
	@Transactional(readOnly = true)
	public CelebrantDTO findById(Long id) {
		Optional<Celebrant> obj = repository.findById(id);
		Celebrant entity = obj.orElseThrow(() -> new ResourceNotFoundException("Celebrant not found!"));
		return new CelebrantDTO(entity);
	}
	
	//public List<Celebrant> findByName(String name) {
	//	return repository.findByName(name);
	//}
	
	@Transactional
	public CelebrantDTO insert(CelebrantDTO dto) {
		Celebrant entity = new Celebrant();
		copyDtoToEntity(dto, entity);
		repository.save(entity);
		return new CelebrantDTO(entity);
	}
	
	@Transactional
	public CelebrantDTO update(Long id, CelebrantDTO dto) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Celebrant not found!");
		}
		try {
			Celebrant entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			repository.save(entity);
			return new CelebrantDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Celebrant not found!");
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Celebrant not found!");
		}
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException exception) {
			throw new DatabaseException(exception.getMessage());
		}
	}
	
	private void copyDtoToEntity(CelebrantDTO dto, Celebrant entity) {

		entity.setName(dto.getNameCelebrant());
		entity.setReligiousTitle(dto.getReligiousTitle());
	}
}
