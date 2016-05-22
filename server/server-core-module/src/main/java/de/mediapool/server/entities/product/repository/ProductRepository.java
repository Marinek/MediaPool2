package de.mediapool.server.entities.product.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.product.domain.Product;
import de.mediapool.server.entities.users.domain.User;

public interface ProductRepository extends GraphRepository<Product> {

	public Result<Product> findByTitle(String title);
	
	@Query("MATCH (product:Product) RETURN product order by product.title")
	public Result<Product> findAllOrderByTitle();

	@Query("MATCH (movie:Movie {title:{0}})--(product:Product) RETURN product")
	public Result<Product> findByMovieTitle(String movieTitle);

	@Query("MATCH (u:User)-[r:OWNING]->(p:Product) where ID(p) = {0} RETURN u")
	public Result<User> findOwner(Long productId);
	
	
}
