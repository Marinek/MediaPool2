package de.mediapool.server.entities.product.domain;

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
public abstract class ProductNodeDTO extends NodeDTO {

	private static final long serialVersionUID = 1L;

	private String title;

	private String orginaltitle;

	private Date published;

	private String special;

	private String language;

	private String price;

	private String cover;

	private String description;

	private String ean;

	private String format;

	@RelatedToVia(type = "OWNS", direction = Direction.OUTGOING)
	@Fetch
	private Set<OwnerRelationship> owendProducts = new HashSet<>();

	@Override
	public String getType() {
		return "list";
	}

	public OwnerRelationship owendBy(UserNodeDTO user) {
		OwnerRelationship relation = new OwnerRelationship();

		relation.setSince(new Date());

		relation.setOwnes(this);

		relation.setUser(user);

		owendProducts.add(relation);

		return relation;
	}

}
