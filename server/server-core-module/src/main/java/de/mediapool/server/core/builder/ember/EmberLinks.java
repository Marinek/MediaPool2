package de.mediapool.server.core.builder.ember;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public interface EmberLinks {

	public default ConcurrentMap<String, String> getLinks() {
		return new ConcurrentHashMap<>();
	}
	
}
