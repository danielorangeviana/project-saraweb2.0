package com.br.saraweb.service;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.saraweb.dto.BaptismDataDTO;
import com.br.saraweb.dto.BaptismDataMinDTO;
import com.br.saraweb.dto.ScheduleBaptismDTO;
import com.br.saraweb.entities.BaptismData;
import com.br.saraweb.entities.Celebrant;
import com.br.saraweb.entities.Godparent;
import com.br.saraweb.entities.PageBook;
import com.br.saraweb.entities.Parent;
import com.br.saraweb.repositories.BaptismDataRepository;
import com.br.saraweb.repositories.CelebrantRepository;
import com.br.saraweb.repositories.GodparentRepository;
import com.br.saraweb.repositories.PageBookRepository;
import com.br.saraweb.repositories.ParentRepository;
import com.br.saraweb.exceptions.DatabaseException;
import com.br.saraweb.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class BaptismDataService {

	private final BaptismDataRepository repository;
	private final ParentRepository parentRepository;
	private final GodparentRepository godparentRepository;
	private final PageBookRepository pageBookRepository;
	private final CelebrantRepository celebrantRepository;
	
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
