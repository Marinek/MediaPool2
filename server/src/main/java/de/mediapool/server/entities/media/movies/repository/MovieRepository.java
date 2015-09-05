package de.mediapool.server.entities.media.movies.repository;

import java.util.List;

import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.repository.GraphRepository;

import de.mediapool.server.entities.media.movies.domain.Movie;

public interface MovieRepository extends GraphRepository<Movie> {

	public List<Movie> findByTitle(String title);

	public Result<Movie> findAll();

}
