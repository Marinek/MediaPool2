package de.mediapool.server.entities.media.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.mediapool.server.core.domain.NodeDTO;
import de.mediapool.server.entities.persons.domain.PersonNodeDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NodeEntity
public abstract class MediaNodeDTO extends NodeDTO {

	private static final long serialVersionUID = 1L;

	private String title;

	private String orginaltitle;

	private int publishedYear;

	private String genre;

	private String language;

	private String award;

	private String cover;

	private String description;

	private String contentType;

	@JsonIgnore
	@RelatedToVia(type = "IS_PART", direction = Direction.INCOMING)
	@Fetch
	private Set<PersonsRelationship> persons = new HashSet<>();

	public PersonsRelationship addPerson(PersonNodeDTO person, String role) {
		PersonsRelationship relation = new PersonsRelationship();

		relation.setMedia(this);

		relation.setPerson(person);

		relation.setRole(role);

		persons.add(relation);

		return relation;
	}

	public PersonsRelationship addPerson(PersonNodeDTO person) {
		return addPerson(person, person.getMainRole());
	}

	@Override
	public String toString() {
		return "MediaNodeDTO [title=" + title + "]";
	}

	public MediaNodeDTO(String title, String orginaltitle, String genre, String language, String award, String cover, String description, String contentType, int publishedYear) {
		super();
		this.title = title;
		this.orginaltitle = orginaltitle;
		this.publishedYear = publishedYear;
		this.genre = genre;
		this.language = language;
		this.award = award;
		this.cover = cover;
		this.description = description;
		this.contentType = contentType;
	}

	public MediaNodeDTO() {
		super();
	}

}
