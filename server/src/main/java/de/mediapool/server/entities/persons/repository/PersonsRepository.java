package de.mediapool.server.entities.persons.repository;

import java.util.List;

import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.repository.CrudRepository;

import de.mediapool.server.entities.persons.domain.Person;

public interface PersonsRepository extends CrudRepository<Person, String> {

	public List<Person> findByLastName(String name);

	public Result<Person> findAll();

}
