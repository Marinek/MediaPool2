package de.mediapool.server.media.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import de.mediapool.server.core.domain.json.NodeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NodeEntity
public class MediaNodeDTO extends NodeDTO {

	private static final long serialVersionUID = 1L;

	private String name;

	private Date published;

	@RelatedTo(type = "ACTOR_IN", direction = Direction.INCOMING)
	private @Fetch Set<ActorNodeDTO> actors;
	
    public void addActor(ActorNodeDTO actor) {
        if (actors == null) {
        	actors = new HashSet<ActorNodeDTO>();
        }
        actors.add(actor);
    }

	@Override
	public String getType() {
		return "media";
	}

}
