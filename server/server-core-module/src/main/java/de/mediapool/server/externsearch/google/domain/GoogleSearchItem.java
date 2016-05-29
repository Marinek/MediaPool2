package de.mediapool.server.externsearch.google.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class GoogleSearchItem {

	private String title;
	
	@JsonProperty("pagemap")
	private GoogleSearchPageMapItem pagemap;
	
	
}
