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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String title;

	private String p_orginaltitle;

	private Date p_published;

	private String p_special;

	private String p_language;

	private String p_price;

	private String p_cover;

	private String p_description;

	private String p_ean;

	private String p_format;

}
