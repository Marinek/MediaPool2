package de.mediapool.server.media.domain;

import java.util.Date;

import de.mediapool.server.core.domain.json.AttributeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MediaAttributeDTO extends AttributeDTO {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private Date published;

	@Override
	public String getType() {
		return "media";
	}

}
