package de.mediapool.server.neo4j.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;

@SuppressWarnings("deprecation")
@Configuration
@EnableNeo4jRepositories(basePackages = "de.mediapool.server")
public class ApplicationConfig extends Neo4jConfiguration {

	public ApplicationConfig() {
		setBasePackage("de.mediapool.server");
	}

	@Bean
	GraphDatabaseService graphDatabaseService() {
		// Hier muss man vorerst damit leben, da die neue Implementierung nicht
		// alle Features unterstützt!
		SpringRestGraphDatabase  springCypherRestGraphDatabase = new SpringRestGraphDatabase  ("http://localhost:7474/db/data/", "neo4j", "test");
		
		return springCypherRestGraphDatabase;
	}
}