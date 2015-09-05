package de.mediapool.server.entities.users.repository;

import java.util.List;

import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.users.domain.User;

public interface UserRepository extends GraphRepository<User> {

	public User findByUsername(String username);

	public List<User> findAllByUsername(String username);

	public Result<User> findAll();
}
