package de.mediapool.server.externsearch.google.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties
@Getter
@Setter
public class GoogleSearchImageItem {

	private String src;
}
