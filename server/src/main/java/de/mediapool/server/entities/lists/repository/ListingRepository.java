package de.mediapool.server.entities.lists.repository;

import java.util.List;

import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.lists.domain.Listing;

public interface ListingRepository extends GraphRepository<Listing> {

	public List<Listing> findByTitle(String title);

	public Result<Listing> findAll();

}