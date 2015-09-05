package de.mediapool.server.entities.lists.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import de.mediapool.server.core.domain.NodeDTO;
import de.mediapool.server.entities.domain.Visibility;
import de.mediapool.server.entities.product.domain.ProductNodeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NodeEntity
public class ListNodeDTO extends NodeDTO {

	private static final long serialVersionUID = 1L;

	private String title;

	private Date created;

	private Visibility visibility = Visibility.PRIVATE;

	@RelatedToVia(type = "LISTED", direction = Direction.OUTGOING)
	@Fetch
	private Set<ListedRelationship> listedProducts;

	public ListNodeDTO(String title, Date created, Visibility visibility, Set<ListedRelationship> listedProducts) {
		super();
		this.title = title;
		this.created = created;
		this.visibility = visibility;
		this.listedProducts = listedProducts;
	}

	public ListNodeDTO(String title) {
		this(title, new Date(), Visibility.PRIVATE, new HashSet<>());
	}

	public ListNodeDTO() {
		super();
	}

	@Override
	public String getType() {
		return "list";
	}

	public ListedRelationship addToList(ProductNodeDTO product) {
		if (listedProducts == null) {
			listedProducts = new HashSet<>();
		}

		if (listedProducts.size() > 0) {
			for (ListedRelationship lrs : listedProducts) {
				if (product.equals(lrs.getListItem())) {
					return lrs;
				}
			}
		}

		ListedRelationship relation = new ListedRelationship();

		relation.setSince(new Date());

		relation.setList(this);
		relation.setListItem(product);

		listedProducts.add(relation);

		return relation;
	}

	@Override
	public String toString() {
		return "ListNodeDTO [title=" + title + "]";
	}

}
