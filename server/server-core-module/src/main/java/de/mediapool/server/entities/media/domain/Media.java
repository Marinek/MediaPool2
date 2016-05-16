package de.mediapool.server.entities.media.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import de.mediapool.server.core.builder.ember.EmberLinks;
import de.mediapool.server.core.domain.Node;
import de.mediapool.server.entities.persons.domain.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NodeEntity
public abstract class Media extends Node implements EmberLinks {

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

    @JsonIdentityReference(alwaysAsId = true)
	@RelatedToVia(type = "IS_PART", direction = Direction.INCOMING)
	@Fetch
	private Set<PersonsRelationship> persons = new HashSet<>();

	public PersonsRelationship addPerson(Person person, String role) {
		PersonsRelationship relation = new PersonsRelationship();

		relation.setMedia(this);

		relation.setPerson(person);

		relation.setRole(role);

		persons.add(relation);

		return relation;
	}
	
	public PersonsRelationship addPerson(Person person) {
		return addPerson(person, person.getMainRole());
	}
	
	@Override
	public ConcurrentMap<String, String> getLinks() {
		ConcurrentMap<String, String> links = EmberLinks.super.getLinks();
		
		links.put("persons", "persons");
		
		return links;
	}

	@Override
	public String toString() {
		return "MediaNodeDTO [title=" + title + "]";
	}

	public Media(String title, String orginaltitle, String genre, String language, String award, String cover, String description, String contentType, int publishedYear) {
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

	public Media() {
		super();
	}

}
