package de.mediapool.server.entities.lists.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.lists.domain.ListNodeDTO;

public interface ListRepository extends GraphRepository<ListNodeDTO> {

}