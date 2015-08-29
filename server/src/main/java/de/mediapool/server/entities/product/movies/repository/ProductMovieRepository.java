package de.mediapool.server.entities.product.movies.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.media.movies.domain.MovieNodeDTO;
import de.mediapool.server.entities.product.movies.domain.ProductMovieNodeDTO;

public interface ProductMovieRepository extends GraphRepository<ProductMovieNodeDTO> {

	public ProductMovieNodeDTO findById(String id);
	
	public ProductMovieNodeDTO findByNameAndId(String name, String id);

}
