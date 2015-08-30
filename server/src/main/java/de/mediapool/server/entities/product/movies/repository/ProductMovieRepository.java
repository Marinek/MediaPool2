package de.mediapool.server.entities.product.movies.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.product.movies.domain.ProductMovieNodeDTO;

public interface ProductMovieRepository extends GraphRepository<ProductMovieNodeDTO> {

	public List<ProductMovieNodeDTO> findByTitle(String title);

}
