package de.mediapool.server.entities.media.movies.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.media.movies.domain.MovieNodeDTO;

public interface MovieRepository extends GraphRepository<MovieNodeDTO> {

	public MovieNodeDTO findByName(String name);

}
