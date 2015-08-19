package de.mediapool.server.media.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import de.mediapool.server.core.domain.json.AttributeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NodeEntity
public class MediaAttributeDTO extends AttributeDTO {

	private static final long serialVersionUID = 1L;

	private String name;

	private Date published;

	@GraphId
	Long graphId;

	@RelatedTo(type = "TEAMMATE", direction = Direction.BOTH)
	public @Fetch Set<MediaAttributeDTO> teammates;
	

    public void worksWith(MediaAttributeDTO person) {
        if (teammates == null) {
            teammates = new HashSet<MediaAttributeDTO>();
        }
        teammates.add(person);
    }

	@Override
	public String getType() {
		return "media";
	}

}
