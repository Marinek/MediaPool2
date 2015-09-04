package de.mediapool.server.entities.media.movies.domain;

import org.springframework.data.neo4j.annotation.NodeEntity;

import de.mediapool.server.entities.media.domain.MediaNodeDTO;
import lombok.Getter;
import lombok.Setter;

@NodeEntity
@Getter
@Setter
public class MovieNodeDTO extends MediaNodeDTO {

	private static final long serialVersionUID = 1L;

	private int duration;
	private int age_restriction;

	@Override
	public String getType() {
		return "MOVIE";
	}

	public MovieNodeDTO(String title, String orginaltitle, int publishedYear, String genre, String language, String award, String cover, String description, String contentType, int duration,
			int age_restriction) {
		super(title, orginaltitle, genre, language, award, cover, description, contentType, publishedYear);
		this.duration = duration;
		this.age_restriction = age_restriction;
	}

	public MovieNodeDTO() {
		super();
	}

}