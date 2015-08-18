package de.mediapool.server.core.domain.json;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AttributeDTO extends DataTransfereObject {

	private static final long serialVersionUID = 1L;

	private String id;
	
	@JsonIgnore
	public abstract String getType();
	
	@JsonIgnore
	public String getId() {
		return id;
	}
	
}
