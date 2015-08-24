package de.mediapool.server.core.domain;

import org.springframework.data.neo4j.annotation.GraphId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class NodeDTO implements DataTransfereObject {

	private static final long serialVersionUID = 1L;

	private String id;
	
	@GraphId
	private Long graphId;
	
	public abstract String getType();
}
