package de.mediapool.server.entities.lists.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import de.mediapool.server.core.domain.NodeDTO;
import de.mediapool.server.entities.domain.Visibility;
import de.mediapool.server.entities.media.movies.domain.MovieNodeDTO;
import de.mediapool.server.entities.users.domain.UserNodeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NodeEntity
public class ListNodeDTO extends NodeDTO {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private Date created;
	
	private Visibility visibility = Visibility.PRIVATE;
	
	@RelatedTo(type="CREATED", direction=Direction.INCOMING)
    private @Fetch UserNodeDTO createdBy;
	
	@RelatedToVia(type="LISTED", direction=Direction.OUTGOING)
	@Fetch
	private Set<ListedRelationship> listedProducts = new HashSet<>();

	@Override
	public String getType() {
		return "list";
	}
	
	public ListedRelationship addToList(MovieNodeDTO movie) {
		ListedRelationship relation = new ListedRelationship();
		
		relation.setSince(new Date());
		
		relation.setList(this);
		relation.setListItem(movie);
		
		listedProducts.add(relation);
		
		return relation;
	}

}
