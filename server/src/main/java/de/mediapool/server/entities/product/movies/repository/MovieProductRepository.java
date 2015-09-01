package de.mediapool.server.entities.product.movies.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.product.movies.domain.MovieProductNodeDTO;

public interface MovieProductRepository extends GraphRepository<MovieProductNodeDTO> {

	public List<MovieProductNodeDTO> findByTitle(String title);

}
