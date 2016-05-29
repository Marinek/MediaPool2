package de.mediapool.server.externsearch.components;

import java.util.List;

import de.mediapool.server.externsearch.domain.ExternalSearchResult;

public interface ExternalSearchCompoenent {

	public List<ExternalSearchResult> search (String searchQuery);
	
	public String getName();
}
