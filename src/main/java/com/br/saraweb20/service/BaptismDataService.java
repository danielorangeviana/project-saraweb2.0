package com.br.saraweb20.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.saraweb20.dto.BaptismDataDTO;
import com.br.saraweb20.dto.BaptismDataMinDTO;
import com.br.saraweb20.dto.ScheduleBaptismDTO;
import com.br.saraweb20.entities.BaptismData;
import com.br.saraweb20.entities.Celebrant;
import com.br.saraweb20.entities.Godparent;
import com.br.saraweb20.entities.PageBook;
import com.br.saraweb20.entities.Parent;
import com.br.saraweb20.repositories.BaptismDataRepository;
import com.br.saraweb20.repositories.CelebrantRepository;
import com.br.saraweb20.repositories.GodparentRepository;
import com.br.saraweb20.repositories.PageBookRepository;
import com.br.saraweb20.repositories.ParentRepository;
import com.br.saraweb20.service.exceptions.DatabaseException;
import com.br.saraweb20.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BaptismDataService {
	
	@Autowired
	private BaptismDataRepository repository;
	
	@Autowired
	private ParentRepository parentRepository;
	
	@Autowired
	private GodparentRepository godparentRepository;
	
	@Autowired
	private PageBookRepository pageBookRepository;
	
	@Autowired
	private CelebrantRepository celebrantRepository;
	
	@Transactional(readOnly = true)
	public Page<BaptismDataMinDTO> findAllPagedByName(String nameChildren, Pageable pageable) {
		Page<BaptismData> page = repository.searchByName(nameChildren, pageable);
		return page.map(x -> new BaptismDataMinDTO(x));
	}

	@Transactional(readOnly = true)
	public BaptismDataMinDTO findById(Long id) {
		Optional<BaptismData> obj = repository.findById(id);
		BaptismData entity = obj.orElseThrow(() -> new ResourceNotFoundException("Baptism not found!"));
		return new BaptismDataMinDTO(entity);
	}

	@Transactional
	public BaptismDataDTO insert(BaptismDataDTO dto) {
		BaptismData entity = new BaptismData();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new BaptismDataDTO(entity);
	}
	
	@Transactional
	public BaptismDataDTO scheduleDateBaptism(Long id, ScheduleBaptismDTO dto) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Baptism not found!");
		}
		try {
			BaptismData entity = repository.getReferenceById(id);
			entity.setDateBaptism(dto.getDateBaptism());
			entity = repository.save(entity);
			return new BaptismDataDTO(entity);
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Baptism not found!");
		}
	}

	@Transactional
	public BaptismDataDTO update(Long id, BaptismDataDTO dto) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Baptism not found!");
		}
		try {
			BaptismData entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new BaptismDataDTO(entity);
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Baptism not found!");
		}		
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Baptism not found!");
		}
		try {
			repository.deleteById(id);
		}
		catch (DataIntegrityViolationException exception) {
			throw new DatabaseException(exception.getMessage());
		}
	}
	
	private void copyDtoToEntity(BaptismDataDTO dto, BaptismData entity) {

		entity.setNumberTerm(dto.getNumberTerm());
		entity.setNameChildren(dto.getNameChildren());
		entity.setDateBirth(dto.getDateBirth());
		entity.setDateBaptism(dto.getDateBaptism());
		
		Parent parent = parentRepository.findById(dto.getParentId())
				.orElseThrow(() -> new ResourceNotFoundException("Parent not found!"));
		entity.setParents(parent);
		
		Godparent godparent = godparentRepository.findById(dto.getGodparentId())
				.orElseThrow(() -> new ResourceNotFoundException("Godparent not found!"));
		entity.setGodparents(godparent);
		
		PageBook pageBook = pageBookRepository.findById(dto.getPageBookId())
				.orElseThrow(() -> new ResourceNotFoundException("Page not found!"));
		entity.setPageBook(pageBook);
		
		Celebrant celebrant =  celebrantRepository.findById(dto.getCelebrantId())
				.orElseThrow(() -> new ResourceNotFoundException("Celebrant not found!"));
		entity.setCelebrant(celebrant);
		
	}
}
