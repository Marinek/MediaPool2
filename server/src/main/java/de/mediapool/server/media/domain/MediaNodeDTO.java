package de.mediapool.server.media.domain;

import java.util.Date;

import org.springframework.data.neo4j.annotation.NodeEntity;

import de.mediapool.server.core.domain.NodeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NodeEntity
public abstract class MediaNodeDTO extends NodeDTO {

	private static final long serialVersionUID = 1L;

	private String name;

	private Date published;

}
