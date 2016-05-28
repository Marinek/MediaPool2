package de.mediapool.server.entities.lists.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import de.mediapool.server.core.domain.Node;
import de.mediapool.server.entities.domain.Visibility;
import de.mediapool.server.entities.product.domain.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NodeEntity
public class ProductList extends Node {

	private static final long serialVersionUID = 1L;

	private String title;

	private Date created;
	
	private String description;

	private Visibility visibility = Visibility.PRIVATE;

	@RelatedToVia(type = "LISTED", direction = Direction.OUTGOING)
	@Fetch
	private Set<ListedRelationship> listedProducts;

	public ProductList(String title, Date created, Visibility visibility, Set<ListedRelationship> listedProducts) {
		super();
		this.title = title;
		this.created = created;
		this.visibility = visibility;
		this.listedProducts = listedProducts;
	}

	public ProductList(String title) {
		this(title, new Date(), Visibility.PRIVATE, new HashSet<>());
	}

	public ProductList() {
		super();
	}

	public ListedRelationship addToList(Product product) {
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
