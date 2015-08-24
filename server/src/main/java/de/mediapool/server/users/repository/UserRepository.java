package de.mediapool.server.users.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.users.domain.UserNodeDTO;

public interface UserRepository extends GraphRepository<UserNodeDTO> {

	public UserNodeDTO findById(String id);
	
	public UserNodeDTO findByUsername(String username);
}
