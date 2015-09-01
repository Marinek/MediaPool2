package de.mediapool.server.entities.users.domain;

import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import de.mediapool.server.core.domain.RelationShipDTO;
import de.mediapool.server.entities.domain.Visibility;
import de.mediapool.server.entities.product.domain.ProductNodeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RelationshipEntity(type = "OWNING")
public class OwnerRelationship extends RelationShipDTO {

	private static final long serialVersionUID = 1L;

	@EndNode
	@Fetch
	private ProductNodeDTO ownes;

	@StartNode
	private UserNodeDTO user;

	@GraphProperty
	private Date since;

	@GraphProperty
	private boolean known;

	@GraphProperty
	private int rating;

	@GraphProperty
	private Visibility visible;

	@GraphProperty
	private Date lastUsed;

	@GraphProperty
	private String condition;

	@GraphProperty
	private String storageLocation;

	@GraphProperty
	private int storageNumber;

}
