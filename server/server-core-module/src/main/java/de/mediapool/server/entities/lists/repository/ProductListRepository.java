package de.mediapool.server.entities.lists.repository;

import java.util.List;

import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.lists.domain.ProductList;

public interface ProductListRepository extends GraphRepository<ProductList> {

	public List<ProductList> findByTitle(String title);

	public Result<ProductList> findAll();

}