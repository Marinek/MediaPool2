package de.mediapool.server.entities.lists.domain;

import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphProperty;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.mediapool.server.core.domain.Relationship;
import de.mediapool.server.entities.product.domain.Product;
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
	private Product listItem;

	@StartNode
	@Fetch
	private ProductList list;

	@GraphProperty
	private Date since;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		result = prime * result + ((listItem == null) ? 0 : listItem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListedRelationship other = (ListedRelationship) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		if (listItem == null) {
			if (other.listItem != null)
				return false;
		} else if (!listItem.equals(other.listItem))
			return false;
		return true;
	}
}
