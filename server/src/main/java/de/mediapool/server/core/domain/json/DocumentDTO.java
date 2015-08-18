package de.mediapool.server.core.domain.json;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DocumentDTO<T extends AttributeDTO> extends DataTransfereObject {

	private static final long serialVersionUID = 1L;
	
	private List<ResourceDTO<T>> data = new ArrayList<>();
	
	private List<ErrorDTO> errors;
	
}
