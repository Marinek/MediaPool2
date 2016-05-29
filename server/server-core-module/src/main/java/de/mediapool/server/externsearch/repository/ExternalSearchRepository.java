package de.mediapool.server.externsearch.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import de.mediapool.server.core.executor.ChainExecuterService;
import de.mediapool.server.core.executor.Executor;
import de.mediapool.server.externsearch.components.ExternalSearchCompoenent;
import de.mediapool.server.externsearch.domain.ExternalSearchResult;

@Repository
public class ExternalSearchRepository {

	@Autowired
	private ChainExecuterService executorService;
	
	@Cacheable("googleSearchCache")
	public List<ExternalSearchResult> executeSearch(final String queryString) {
		final List<ExternalSearchResult> externalSearchResultList = new ArrayList<>();
		
		executorService.execute(ExternalSearchCompoenent.class, new Executor<ExternalSearchCompoenent>() {

			@Override
			public void execute(ExternalSearchCompoenent bean) {
				List<ExternalSearchResult> searchResult = bean.search(queryString);
				
				externalSearchResultList.addAll(searchResult);
			}
			
		});
		
		return externalSearchResultList;
	}
}
