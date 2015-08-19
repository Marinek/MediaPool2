package de.mediapool.server.core.domain.json;

import org.springframework.data.neo4j.annotation.GraphId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class NodeDTO extends DataTransfereObject {

	private static final long serialVersionUID = 1L;

	private String id;
	
	@GraphId
	private Long graphId;
	
	@JsonIgnore
	public abstract String getType();
	
	@JsonIgnore
	public String getId() {
		return id;
	}
	
}
