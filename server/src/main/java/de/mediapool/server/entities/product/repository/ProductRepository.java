package de.mediapool.server.entities.product.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.product.domain.ProductNodeDTO;

public interface ProductRepository extends GraphRepository<ProductNodeDTO> {

	public List<ProductNodeDTO> findByTitle(String title);

}
