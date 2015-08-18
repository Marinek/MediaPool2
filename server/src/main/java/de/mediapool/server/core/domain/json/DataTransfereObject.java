package de.mediapool.server.core.domain.json;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This class is the parent class of every DataTransfereObjects as there are specified in 
 * <a href="http://jsonapi.org/format/">http://jsonapi.org/format/</a>. This
 * specification targets a common format (for json serialization) as it is used 
 * by ember-data.    
 * 
 * @author marcinek
 * @since 1.0.0
 */
@Getter
@Setter
@ToString
public abstract class DataTransfereObject extends Object implements Serializable {

	private static final long serialVersionUID = 1L;
	
}
