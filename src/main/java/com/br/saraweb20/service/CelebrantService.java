package com.br.saraweb20.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.saraweb20.dto.CelebrantDTO;
import com.br.saraweb20.entities.Celebrant;
import com.br.saraweb20.repositories.CelebrantRepository;
import com.br.saraweb20.service.exceptions.DatabaseException;
import com.br.saraweb20.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CelebrantService {
	
	@Autowired
	private CelebrantRepository repository;
	
	@Transactional(readOnly = true)
	public Page<CelebrantDTO> findAllPaged(Pageable pageable) {
		Page<Celebrant> list = repository.findAll(pageable);
		return list.map(x -> new CelebrantDTO(x));
	}
	
	@Transactional(readOnly = true)
	public CelebrantDTO findById(Long id) {
		Optional<Celebrant> obj = repository.findById(id);
		Celebrant entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
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
			throw new ResourceNotFoundException(id);
		}
		try {
			Celebrant entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			repository.save(entity);
			return new CelebrantDTO(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException(id);
		}
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException exception) {
			throw new DatabaseException(exception.getMessage());
		}
	}
	
	private void copyDtoToEntity(CelebrantDTO dto, Celebrant entity) {

		entity.setName(dto.getName());
		entity.setReligiousTitle(dto.getReligiousTitle());
	}
}
