package de.mediapool.server.entities.persons.domain;

import java.util.Date;

import org.springframework.data.neo4j.annotation.NodeEntity;

import de.mediapool.server.core.domain.NodeDTO;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@NodeEntity
public class PersonNodeDTO extends NodeDTO {

	private String firstName;
	private String lastName;
	
	private Date birthdate;
	
	private String country;
	private String gender;
	
	private String role;
	private boolean business;
	
	private String image;
	
		
	@Override
	public String getType() {
		return "PERSON";
	}
}
