package de.mediapool.server.entities.product.domain;

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

}
