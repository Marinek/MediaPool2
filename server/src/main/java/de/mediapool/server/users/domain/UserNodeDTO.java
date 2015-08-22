package de.mediapool.server.users.domain;

import de.mediapool.server.core.domain.NodeDTO;

public class UserNodeDTO extends NodeDTO {

	private static final long serialVersionUID = 1L;

	@Override
	public String getType() {
		return "user";
	}

}
