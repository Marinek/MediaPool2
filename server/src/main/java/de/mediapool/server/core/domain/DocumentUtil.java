package de.mediapool.server.core.domain;

import de.mediapool.server.core.domain.json.NodeDTO;
import de.mediapool.server.core.domain.json.DocumentDTO;
import de.mediapool.server.core.domain.json.ResourceDTO;

public class DocumentUtil {
	
	public static <T extends NodeDTO> DocumentDTO<T> getDocument(T attribute) {
		return getDocument(attribute, new DocumentDTO<>());
	}

	public static <T extends NodeDTO> DocumentDTO<T> getDocument(T attribute, DocumentDTO<T> documentDTO) {
		ResourceDTO<T> resourceDTO = new ResourceDTO<T>();
		
		documentDTO.getData().add(resourceDTO);
		
		resourceDTO.setAttributes(attribute);
		resourceDTO.setId(attribute.getId());
		resourceDTO.setType(attribute.getType());
		
		return documentDTO;
	}
}
