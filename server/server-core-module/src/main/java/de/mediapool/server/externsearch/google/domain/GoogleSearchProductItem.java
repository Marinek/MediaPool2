package de.mediapool.server.externsearch.google.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class GoogleSearchProductItem {

	private String name;
	private String image;
	private String category;
	private String manufacturer;
	private String releasedate;
	private String gtin13;
	
}
