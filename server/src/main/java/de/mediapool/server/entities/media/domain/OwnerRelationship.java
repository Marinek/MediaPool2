package de.mediapool.server.entities.media.domain;

import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.mediapool.server.core.domain.RelationShipDTO;
import de.mediapool.server.entities.users.domain.UserNodeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RelationshipEntity(type="OWNING")
public class OwnerRelationship extends RelationShipDTO {
	
	private static final long serialVersionUID = 1L;

	@EndNode
	@JsonIgnore
	private MediaNodeDTO ownes;
	
	@StartNode
	@Fetch
	private UserNodeDTO user;
	
	@GraphProperty
	private Date since;
	
}
