package de.mediapool.server.entities.persons.repository;

import org.springframework.data.repository.CrudRepository;

import de.mediapool.server.entities.persons.domain.PersonNodeDTO;

public interface PersonsRepository extends CrudRepository<PersonNodeDTO, String> {

	public PersonsRepository findByLastName(String name);

}
