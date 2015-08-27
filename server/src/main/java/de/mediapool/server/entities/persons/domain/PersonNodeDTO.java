package de.mediapool.server.entities.persons.domain;

import de.mediapool.server.core.domain.NodeDTO;

public class PersonNodeDTO extends NodeDTO {

	private static final long serialVersionUID = 1L;

	@Override
	public String getType() {
		return "person";
	}

}
