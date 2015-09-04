package de.mediapool.server.entities.lists.repository;

import java.util.List;

import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.lists.domain.ListNodeDTO;

public interface ListRepository extends GraphRepository<ListNodeDTO> {

	public List<ListNodeDTO> findByTitle(String title);

	public Result<ListNodeDTO> findAll();

}