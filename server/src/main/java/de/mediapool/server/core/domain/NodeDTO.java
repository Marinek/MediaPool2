package de.mediapool.server.core.domain;

import org.springframework.data.neo4j.annotation.GraphProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class NodeDTO extends GraphEntryDTO {

	private static final long serialVersionUID = 1L;

	@GraphProperty
	public abstract String getType();
}
