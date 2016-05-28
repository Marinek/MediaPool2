package de.mediapool.server.externsearch.domain;

import de.mediapool.server.core.domain.DataTransfereObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExternalSearchResult implements DataTransfereObject {

	private static final long serialVersionUID = 1L;
	
	private String title;
	
	private String imgSource;
	
	private String source;
	
	private String externalSource;

}
