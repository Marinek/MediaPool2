package de.mediapool.server.externsearch.google;

import java.util.Map;

public interface ExternalSearchCompoenent {

	public Map<?, ?> search (String searchQuery);
	
	public String getName();
}
