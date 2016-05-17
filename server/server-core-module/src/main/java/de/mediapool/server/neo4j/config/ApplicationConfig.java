package de.mediapool.server.neo4j.config;

import javax.annotation.PostConstruct;

import org.neo4j.graphdb.GraphDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;

@Configuration
@EnableNeo4jRepositories(basePackages = "de.mediapool")
public class ApplicationConfig extends Neo4jConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);
	
	public ApplicationConfig() {
		setBasePackage("de.mediapool");
	}
	
	@PostConstruct
	private void init() {
		logger.debug("Invoking: init()");
	}

	@Bean
	public GraphDatabaseService graphDatabaseService() {
		SpringRestGraphDatabase  springCypherRestGraphDatabase = new SpringRestGraphDatabase  ("http://localhost:7474/db/data/", "neo4j", "test");
		
		return springCypherRestGraphDatabase;
	}
}