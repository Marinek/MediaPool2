package de.mediapool.server.entities.media.movies.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.media.movies.domain.MovieMediaNodeDTO;

public interface MovieMediaRepository extends GraphRepository<MovieMediaNodeDTO> {

	public List<MovieMediaNodeDTO> findByTitle(String title);

}
