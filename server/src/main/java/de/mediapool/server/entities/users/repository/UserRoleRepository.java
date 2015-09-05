package de.mediapool.server.entities.users.repository;

import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.users.domain.UserRole;

public interface UserRoleRepository extends GraphRepository<UserRole> {

	public Result<UserRole> findAll();
}
