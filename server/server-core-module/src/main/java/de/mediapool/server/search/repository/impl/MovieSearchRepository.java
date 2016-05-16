package de.mediapool.server.search.repository.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.TheMovieDbApi;
import com.omertron.themoviedbapi.enumeration.SearchType;
import com.omertron.themoviedbapi.model.movie.MovieInfo;
import com.omertron.themoviedbapi.results.ResultList;

import de.mediapool.server.core.exception.ExceptionCode;
import de.mediapool.server.core.exception.MPServerException;
import de.mediapool.server.entities.media.movies.domain.Movie;
import de.mediapool.server.search.domain.SearchCriteria;
import de.mediapool.server.search.repository.SearchRepository;

@Repository
public class MovieSearchRepository implements SearchRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(MovieSearchRepository.class);

	public Collection<Movie> findBy(SearchCriteria searchCriteria) {
		logger.debug("Invoking: findBy(searchCriteria)");
		
		List<Movie> returnList = new ArrayList<>();
		
		try {
			ResultList<MovieInfo> searchMovie = getTMDB().
					searchMovie(
							searchCriteria.getQuery(),
							searchCriteria.getPage(),
							"de",
							true,
							0,
							0,
							SearchType.PHRASE
						);
			
			for(MovieInfo movie : searchMovie.getResults()) {
				returnList.add(mapMedia(movie));
			}
		} catch (MovieDbException e) {
			throw new MPServerException(ExceptionCode.EXTERN_API_CALL, "Fehler bei der Suche nach Filmen in der TMDB", e);
		}
		
		return returnList;
	}
	
	private Movie mapMedia(MovieInfo movie) {
		Movie media = new Movie();
		
		media.setTitle(movie.getTitle());
		media.setDescription(movie.getOverview());
		// u.s.w.
		
		return media;
	}

	private TheMovieDbApi getTMDB() {
		try {
			return new TheMovieDbApi("52e2a4f480bb00c1969c6a9a338f2b90");
		} catch (MovieDbException e) {
			throw new MPServerException(ExceptionCode.EXTERN_API_CALL, "Fehler beim Erstellen eines API-Objekts zur TMDB", e);
		}
	}
}
