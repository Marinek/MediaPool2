package de.mediapool.server.entities.users.domain;

import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.mediapool.server.core.domain.Relationship;
import de.mediapool.server.entities.domain.Visibility;
import de.mediapool.server.entities.product.domain.ProductNodeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RelationshipEntity(type = "OWNING")
public class OwnerRelationship extends Relationship {

	private static final long serialVersionUID = 1L;

	@EndNode
	@Fetch
	private ProductNodeDTO ownes;

	@StartNode
	@JsonIgnore
	private UserNodeDTO user;

	private Date since;

	private boolean known;

	private int rating;

	private Visibility visible;

	private Date lastUsed;

	private String condition;

	private String storageLocation;

	private int storageNumber;

}
