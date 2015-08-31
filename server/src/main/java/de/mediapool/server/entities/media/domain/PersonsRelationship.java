package de.mediapool.server.entities.media.domain;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.mediapool.server.core.domain.RelationShipDTO;
import de.mediapool.server.entities.persons.domain.PersonNodeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RelationshipEntity(type = "IS_PART")
public class PersonsRelationship extends RelationShipDTO {

	private static final long serialVersionUID = 1L;

	@EndNode
	@JsonIgnore
	private MediaNodeDTO media;

	@StartNode
	@Fetch
	private PersonNodeDTO person;

	@GraphProperty
	private String role;

}
