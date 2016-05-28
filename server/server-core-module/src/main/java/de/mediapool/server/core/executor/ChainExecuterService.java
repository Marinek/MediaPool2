package de.mediapool.server.core.executor;

import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ChainExecuterService {

	private static final Logger logger = LoggerFactory.getLogger(ChainExecuterService.class);
	
	@Autowired
	private ApplicationContext appCtx;
	
	public <T> void execute (Class<T> type, Executor<T> executor ) {
		logger.debug("Invoking: execute(type, executor)");
		
		Map<String, T> beansOfType = appCtx.getBeansOfType(type);
		
		for(Entry<String, T> beanEntry : beansOfType.entrySet()) {
			logger.trace("Executing '" + beanEntry.getKey() + "'");
			executor.execute(beanEntry.getValue());
		}
		
	}
}
