package de.mediapool.server.externsearch.google.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties
@Getter
@Setter
public class GoogleSearchPageMapItem {

	private List<GoogleSearchProductItem> product;
	
	private List<GoogleSearchImageItem> cse_image;
}
