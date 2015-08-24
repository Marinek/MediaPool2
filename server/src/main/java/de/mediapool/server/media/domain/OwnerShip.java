package de.mediapool.server.media.domain;

import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.mediapool.server.core.domain.NodeDTO;
import de.mediapool.server.users.domain.UserNodeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RelationshipEntity(type="OWNING")
public class OwnerShip extends NodeDTO {
	
	private static final long serialVersionUID = 1L;

	@EndNode
	@JsonIgnore
	private MediaNodeDTO ownes;
	
	@StartNode
	private UserNodeDTO user;
	
	@GraphProperty
	private Date since;
	
	@Override
	public String getType() {
		return "ownership";
	}
	
}
