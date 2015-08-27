package de.mediapool.server.entities.persons.actors.domain;

import java.util.Date;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.mediapool.server.entities.media.domain.MediaNodeDTO;
import de.mediapool.server.entities.persons.domain.PersonNodeDTO;
import lombok.Getter;
import lombok.Setter;

@NodeEntity
@Getter
@Setter
public class ActorNodeDTO extends PersonNodeDTO {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@RelatedTo(type = "ACTOR", direction = Direction.BOTH)
	private @Fetch Set<MediaNodeDTO> actsIn;
	
	private String name;
	
	private Date birthdate;
	
	@Override
	public String getType() {
		return "actor";
	}

}
