package de.mediapool.server.entities.product.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.mediapool.server.core.domain.NodeDTO;
import de.mediapool.server.entities.media.movies.domain.MovieNodeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@ToString
@NodeEntity
public abstract class ProductNodeDTO extends NodeDTO {

	private String p_duration;
	
	private int p_age_restriction;
	
	
	@JsonIgnore
	@RelatedTo(type = "IS_ON", direction = Direction.INCOMING)
	private @Fetch Set<MovieNodeDTO> movies;
	
	public void addPerson(MovieNodeDTO movie) {
		if (movies == null) {
			movies = new HashSet<MovieNodeDTO>();
		}
		movies.add(movie);
	}
	
	
	
	

}
