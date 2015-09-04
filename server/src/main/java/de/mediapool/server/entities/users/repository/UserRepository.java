package de.mediapool.server.entities.users.repository;

import java.util.List;

import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.users.domain.UserNodeDTO;

public interface UserRepository extends GraphRepository<UserNodeDTO> {

	public UserNodeDTO findByUsername(String username);

	public List<UserNodeDTO> findAllByUsername(String username);

	public Result<UserNodeDTO> findAll();
}
