package de.mediapool.server.entities.persons.domain;

import java.util.Date;

import org.springframework.data.neo4j.annotation.NodeEntity;

import de.mediapool.server.core.domain.NodeDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NodeEntity
public class PersonNodeDTO extends NodeDTO {

	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;

	private Date birthdate;

	private String country;
	private String gender;

	private String mainRole;
	private boolean business;

	private String image;

	public PersonNodeDTO(String firstName, String lastName, Date birthdate, String country, String gender, String mainRole, boolean business, String image) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.country = country;
		this.gender = gender;
		this.mainRole = mainRole;
		this.business = business;
		this.image = image;
	}

	public PersonNodeDTO() {
		super();
	}

	@Override
	public String getType() {
		return "PERSON";
	}

	@Override
	public String toString() {
		return "PersonNodeDTO [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
