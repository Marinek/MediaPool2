package de.mediapool.server.entities.product.repository;

import java.util.List;

import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.product.domain.Product;

public interface ProductRepository extends GraphRepository<Product> {

	public List<Product> findByTitle(String title);

	// @Query(MATCH(n))
	public Result<Product> findAll();

}
