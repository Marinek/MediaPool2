package de.mediapool.server.core.domain;

import org.springframework.data.neo4j.annotation.GraphProperty;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public abstract class NodeDTO extends GraphEntryDTO {


	private String id;

	@GraphProperty
	public abstract String getType();
}
