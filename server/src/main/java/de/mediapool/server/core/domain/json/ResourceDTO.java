package de.mediapool.server.core.domain.json;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResourceDTO<T extends NodeDTO> extends DataTransfereObject {

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String type;
	
	private T attributes;
	
}
