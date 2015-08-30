package de.mediapool.server.entities.persons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.mediapool.server.entities.persons.domain.PersonNodeDTO;

public interface PersonsRepository extends CrudRepository<PersonNodeDTO, String> {

	public List<PersonNodeDTO> findByLastName(String name);

}
