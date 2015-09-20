package de.mediapool.server.entities.users.repository;

import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.users.domain.FollowRelationship;

public interface FollowRelationshipRepository extends GraphRepository<FollowRelationship> {

	public Result<FollowRelationship> findAll();
}
