package de.mediapool.server.entities.product.movies.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.mediapool.server.entities.media.movies.domain.MovieNodeDTO;
import de.mediapool.server.entities.product.domain.ProductNodeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NodeEntity
public class ProductMovieNodeDTO extends ProductNodeDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String p_duration;

	private int p_age_restriction;

	@JsonIgnore
	@RelatedTo(type = "IS_ON", direction = Direction.INCOMING)
	private @Fetch Set<MovieNodeDTO> movies;

	public void addMedia(MovieNodeDTO movie) {
		if (movies == null) {
			movies = new HashSet<MovieNodeDTO>();
		}
		movies.add(movie);
	}

	@Override
	public String getType() {
		return "productmovie";
	}

}
