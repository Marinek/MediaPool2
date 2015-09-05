package de.mediapool.server.entities.users.domain;

import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.mediapool.server.core.domain.Relationship;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RelationshipEntity(type = "FOLLOW")
public class FollowRelationship extends Relationship {

	private static final long serialVersionUID = 1L;

	@EndNode
	@Fetch
	private User ownes;

	@StartNode
	@JsonIgnore
	private User user;

	private Date since;

	private boolean confirmed;

	private int rating;

	private FellowshipType fellowshipType;

}
