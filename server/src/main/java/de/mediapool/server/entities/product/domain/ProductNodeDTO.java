package de.mediapool.server.entities.product.domain;

import org.springframework.data.neo4j.annotation.NodeEntity;

import de.mediapool.server.core.domain.NodeDTO;
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

	@Override
	public String getType() {
		return "list";
	}

	@Override
	public String toString() {
		return "ProductNodeDTO [title=" + title + "]";
	}

}
