package de.mediapool.server.entities.media.movies.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.media.movies.domain.MovieNodeDTO;

public interface MovieRepository extends GraphRepository<MovieNodeDTO> {

	public List<MovieNodeDTO> findByTitle(String title);

}
