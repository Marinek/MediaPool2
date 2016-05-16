package de.mediapool.server.entities.search.domain;

import org.springframework.data.neo4j.annotation.NodeEntity;

import de.mediapool.server.core.domain.Node;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NodeEntity
public class SearchQuery extends Node {

	private static final long serialVersionUID = 1L;

	private String searchString;

}
