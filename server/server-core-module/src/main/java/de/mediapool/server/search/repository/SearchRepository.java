package de.mediapool.server.search.repository;

import java.util.Collection;

import de.mediapool.server.entities.media.movies.domain.Movie;
import de.mediapool.server.search.domain.SearchCriteria;

public interface SearchRepository {

	public Collection<Movie> findBy(SearchCriteria searchCriteria);
	
}
