package com.devsuperior.bds02.dto.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repo;
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll() {
		return repo.findAll(Sort.by("name")).stream().map(x-> new CityDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public CityDTO findById(Long id) {
		Optional<City> obj = repo.findById(id);
		if (obj.isEmpty())
			throw new EntityNotFoundException("Entidade de id "+id+" não encontrada");
		return new CityDTO(obj.get());
	}
	
	@Transactional
	public void delete(Long id) {
		findById(id);
		repo.deleteById(id);
	}
}
