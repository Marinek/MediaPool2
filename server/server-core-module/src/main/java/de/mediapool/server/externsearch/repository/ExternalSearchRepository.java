package de.mediapool.server.externsearch.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import de.mediapool.server.core.executor.ChainExecuterService;
import de.mediapool.server.core.executor.Executor;
import de.mediapool.server.externsearch.domain.ExternalSearchResult;
import de.mediapool.server.externsearch.google.ExternalSearchCompoenent;

@Repository
public class ExternalSearchRepository {

	@Autowired
	private ChainExecuterService executorService;
	
	public List<ExternalSearchResult> executeSearch(final String queryString) {
		final List<ExternalSearchResult> externalSearchResultList = new ArrayList<>();
		
		executorService.execute(ExternalSearchCompoenent.class, new Executor<ExternalSearchCompoenent>() {

			@Override
			public void execute(ExternalSearchCompoenent bean) {
				Map<?, ?> searchResult = bean.search(queryString);
				
				externalSearchResultList.addAll(getResults(searchResult));
			}
			
		});
		
		return externalSearchResultList;
	}

	protected Collection<? extends ExternalSearchResult> getResults(Map<?, ?> searchResult) {
		Collection<? extends ExternalSearchResult> resultCollection = new ArrayList<>();
		
		
		
		return resultCollection;
	}
}
