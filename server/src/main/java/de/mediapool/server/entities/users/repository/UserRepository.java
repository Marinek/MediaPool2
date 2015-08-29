package de.mediapool.server.entities.users.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.users.domain.UserNodeDTO;

public interface UserRepository extends GraphRepository<UserNodeDTO> {

	public UserNodeDTO findByUsername(String username);
}
