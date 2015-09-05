package de.mediapool.server.core.domain;

import org.springframework.data.neo4j.annotation.GraphId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GraphEntry implements DataTransfereObject {

	private static final long serialVersionUID = 1L;

	@GraphId
	private Long id;
}
