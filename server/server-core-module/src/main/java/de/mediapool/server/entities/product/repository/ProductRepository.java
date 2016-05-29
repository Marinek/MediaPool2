package de.mediapool.server.entities.product.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.product.domain.Product;
import de.mediapool.server.entities.users.domain.User;


public interface ProductRepository extends GraphRepository<Product> {

	public Result<Product> findByTitle(String title);
	
	@Cacheable(value="SingleProductRepository")
	public Product findOneById(Long id);
	
	@Query("MATCH (product:Product) RETURN product order by product.title")
	@Cacheable(value="ProductRepository")
	public List<Product> findAllOrderByTitle();

	@Query("MATCH (movie:Movie {title:{0}})--(product:Product) RETURN product")
	@Cacheable(value="ProductRepository")
	public List<Product> findByMovieTitle(String movieTitle);

	@Query("MATCH (u:User)-[r:OWNING]->(p:Product) where ID(p) = {0} RETURN u")
	@Cacheable(value="ProductRepository")
	public List<User> findOwner(Long productId);
	
}
