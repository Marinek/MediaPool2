package de.mediapool.server.users.domain;

import org.springframework.data.neo4j.annotation.NodeEntity;

import de.mediapool.server.core.domain.NodeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@NodeEntity
@Getter
@Setter
@ToString
public class UserNodeDTO extends NodeDTO {

	private static final long serialVersionUID = 1L;

	private String username;
	
	private String password;
	
	private Boolean isLocked = false;
	
	@Override
	public String getType() {
		return "user";
	}

}
