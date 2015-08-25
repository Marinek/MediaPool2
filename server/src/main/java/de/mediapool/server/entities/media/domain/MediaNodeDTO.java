package de.mediapool.server.entities.media.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import de.mediapool.server.core.domain.NodeDTO;
import de.mediapool.server.entities.users.domain.UserNodeDTO;
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
	
	@RelatedToVia(type="OWNING", direction=Direction.INCOMING)
	@Fetch
	private Set<OwnerShip> ownedBy = new HashSet<>();
	
	public void ownedBy(UserNodeDTO user, Date since) {
		OwnerShip newOwnerShip = new OwnerShip();
		
		newOwnerShip.setUser(user);
		newOwnerShip.setSince(new Date());
		newOwnerShip.setOwnes(this);
		
		ownedBy.add(newOwnerShip);
	}

}
