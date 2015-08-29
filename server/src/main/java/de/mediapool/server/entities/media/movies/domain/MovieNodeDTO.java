package de.mediapool.server.entities.media.movies.domain;

import org.springframework.data.neo4j.annotation.NodeEntity;

import de.mediapool.server.entities.media.domain.MediaNodeDTO;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@NodeEntity
@Getter
@Setter
public class MovieNodeDTO extends MediaNodeDTO {
	
	private String duration;
	private int age_restriction;

	
	@Override
	public String getType() {
		return "MOVIE";
	}

}