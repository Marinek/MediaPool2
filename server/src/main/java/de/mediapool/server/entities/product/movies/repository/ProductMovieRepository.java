package de.mediapool.server.entities.product.movies.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.product.movies.domain.ProductMovieNodeDTO;

public interface ProductMovieRepository extends GraphRepository<ProductMovieNodeDTO> {

	public ProductMovieNodeDTO findById(String id);

	public ProductMovieNodeDTO findByTitle(String title);

}
