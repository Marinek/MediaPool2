package de.mediapool.server.entities.users.domain;

import org.springframework.data.neo4j.annotation.NodeEntity;

import de.mediapool.server.core.domain.Node;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@NodeEntity
@Getter
@Setter
@ToString
public class UserRole extends Node {

	private static final long serialVersionUID = 1L;

	private String name;

}
