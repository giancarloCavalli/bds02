package com.devsuperior.bds02.dto.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository repo;
	
	@Transactional(readOnly = true)
	public EventDTO findById(Long id) {
		Optional<Event> obj = repo.findById(id);
		if (obj.isEmpty())
			throw new EntityNotFoundException("Entidade de id "+id+" não encontrada");
		return new EventDTO(obj.get());
	}
	
	@Transactional
	public EventDTO update(Long id, EventDTO dto) {
		findById(id);
		City city = new City(dto.getCityId(), null);
		Event obj = new Event(id, dto.getName(), dto.getDate(), dto.getUrl(), city);
		obj = repo.save(obj);
		return new EventDTO(obj);
	}
}
