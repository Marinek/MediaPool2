package de.mediapool.server.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;

public class CustomEhCacheCacheManager extends EhCacheCacheManager {

	private static final Logger logger = LoggerFactory.getLogger(CustomEhCacheCacheManager.class);

	private static final String NO_CACHE_ERROR_MSG = "loadCaches must not return an empty Collection";

	public CustomEhCacheCacheManager(net.sf.ehcache.CacheManager object) {
		super(object);
	}

	@Override
	public void afterPropertiesSet() {
		try {
			super.afterPropertiesSet();
		} catch (IllegalArgumentException e) {
			if (NO_CACHE_ERROR_MSG.equals(e.getMessage())) {
				logger.debug("No cache was defined in ehcache.xml. The error "
						+ "thrown by spring because of this was suppressed.");
			} else {
				throw e;
			}
		}
	}

	@Override
	public Cache getCache(String name) {
		Cache cache = super.getCache(name);
		if (cache == null) {
			logger.debug("No cache with name '"
					+ name
					+ "' is defined in encache.xml. Hence creating the cache dynamically...");
			getCacheManager().addCache(name);
			cache = new EhCacheCache(getCacheManager().getCache(name));
			addCache(cache);
			logger.debug("Cache with name '" + name
					+ "' is created dynamically...");
		}
		return cache;
	}
}