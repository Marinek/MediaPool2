package de.mediapool.server.entities.media.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.mediapool.server.core.domain.NodeDTO;
import de.mediapool.server.entities.persons.domain.PersonNodeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@ToString
@NodeEntity
public abstract class MediaNodeDTO extends NodeDTO {

	private String title;

	private String orginaltitle;

	private Date published;

	private String genre;

	private String language;

	private String award;

	private String cover;

	private String description;

	private String contentType;

	@JsonIgnore
	@RelatedTo(type = "IS_PART", direction = Direction.INCOMING)
	private @Fetch Set<PersonNodeDTO> persons;

	public void addPerson(PersonNodeDTO person) {
		if (persons == null) {
			persons = new HashSet<PersonNodeDTO>();
		}
		persons.add(person);
	}

}
