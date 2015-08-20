package de.mediapool.server.core.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

	public Map<String, Collection<? extends NodeDTO>> getRelationships() {
		return new HashMap<>();
	}
	
	public abstract String getType();
}
