package de.mediapool.server.entities.product.movies.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.mediapool.server.core.domain.NodeDTO;
import de.mediapool.server.entities.media.domain.MediaNodeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@ToString
@NodeEntity
public class ProductMovieNodeDTO extends NodeDTO {

	private String title;

	private String p_orginaltitle;

	private Date p_published;

	private String p_special;

	private String p_language;

	private String p_price;

	private String p_cover;

	private String p_description;

	private String p_ean;

	private String p_format;

	private String p_duration;

	private int p_age_restriction;

	@JsonIgnore
	@RelatedTo(type = "IS_ON", direction = Direction.INCOMING)
	private @Fetch Set<MediaNodeDTO> media;

	public void addPerson(MediaNodeDTO medium) {
		if (media == null) {
			media = new HashSet<MediaNodeDTO>();
		}
		media.add(medium);
	}

	@Override
	public String getType() {
		return "productmovie";
	}

}
