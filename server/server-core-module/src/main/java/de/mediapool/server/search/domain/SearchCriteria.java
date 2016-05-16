package de.mediapool.server.search.domain;

import de.mediapool.server.core.domain.DataTransfereObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria implements DataTransfereObject {

	private static final long serialVersionUID = 1L;

	private String query;
	
	private Integer page;
}
