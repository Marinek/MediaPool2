package de.mediapool.server.entities.product.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.product.domain.Product;

public interface ProductRepository extends GraphRepository<Product> {

	public Result<Product> findByTitle(String title);

	@Query("MATCH (movie:Movie {title:{0}})--(product:Product) RETURN product")
	public Result<Product> findByMovieTitle(String movieTitle);

	// @Query(MATCH(n))
	public Result<Product> findAll();

}
