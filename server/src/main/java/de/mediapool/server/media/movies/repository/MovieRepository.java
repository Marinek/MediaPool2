package de.mediapool.server.media.movies.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.media.movies.domain.MovieNodeDTO;

public interface MovieRepository extends GraphRepository<MovieNodeDTO> {

	public MovieNodeDTO findById(String id);

}
