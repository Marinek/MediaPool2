package de.mediapool.server.media.domain;

import java.util.Date;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import de.mediapool.server.core.domain.json.NodeDTO;
import lombok.Getter;
import lombok.Setter;

@NodeEntity
@Getter
@Setter
public class ActorNodeDTO extends NodeDTO {

	private static final long serialVersionUID = 1L;
	
	@RelatedTo(type = "ACTOR", direction = Direction.BOTH)
	private @Fetch Set<MediaNodeDTO> actsIn;
	
	private String name;
	
	private Date birthdate;
	
	@Override
	public String getType() {
		return "actor";
	}

}
