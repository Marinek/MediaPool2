package de.mediapool.server.entities.lists.domain;

import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.mediapool.server.core.domain.Relationship;
import de.mediapool.server.entities.product.domain.ProductNodeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RelationshipEntity(type = "LISTED")
public class ListedRelationship extends Relationship {

	private static final long serialVersionUID = 1L;

	@EndNode
	@JsonIgnore
	private ProductNodeDTO listItem;

	@StartNode
	@Fetch
	private ListNodeDTO list;

	@GraphProperty
	private Date since;

}
