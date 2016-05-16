package de.mediapool.server.entities.users.repository;

import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.users.domain.OwnerRelationship;

public interface OwnerRelationshipRepository extends GraphRepository<OwnerRelationship> {

	public Result<OwnerRelationship> findAll();
}
