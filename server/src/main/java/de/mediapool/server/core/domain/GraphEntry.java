package de.mediapool.server.core.domain;

import org.springframework.data.neo4j.annotation.GraphId;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class GraphEntry implements DataTransfereObject {

	private static final long serialVersionUID = 1L;

	@GraphId
	private Long id;
}
