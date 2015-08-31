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

@Getter
@Setter
@NodeEntity
public abstract class ProductNodeDTO extends NodeDTO {

	private static final long serialVersionUID = 1L;

	private String title;

	private String orginaltitle;

	private int publishedYear;

	private String special;

	private String language;

	private double price;

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

	@Override
	public String toString() {
		return "ProductNodeDTO [title=" + title + "]";
	}

	public ProductNodeDTO(String title, String orginaltitle, int publishedYear, String special, String language, double price, String cover, String description, String ean, String format) {
		super();
		this.title = title;
		this.orginaltitle = orginaltitle;
		this.publishedYear = publishedYear;
		this.special = special;
		this.language = language;
		this.price = price;
		this.cover = cover;
		this.description = description;
		this.ean = ean;
		this.format = format;
	}

	public ProductNodeDTO() {
		super();
	}

}
