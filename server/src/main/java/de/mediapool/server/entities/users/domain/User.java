package de.mediapool.server.entities.users.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.mediapool.server.core.domain.Node;
import de.mediapool.server.entities.lists.domain.Listing;
import lombok.Getter;
import lombok.Setter;

@NodeEntity
@Getter
@Setter
public class User extends Node {

	private static final long serialVersionUID = 1L;

	private String username;

	@JsonIgnore
	private String password;

	private Boolean isLocked = false;

	@RelatedToVia(type = "OWNS", direction = Direction.OUTGOING)
	@Fetch
	private Set<OwnerRelationship> owendProducts;

	@RelatedToVia(type = "FOLLOW", direction = Direction.OUTGOING)
	private Set<FollowRelationship> followedUser;

	@RelatedTo(type = "CREATED", direction = Direction.OUTGOING)
	@Fetch
	private Set<Listing> createdLists;

	@RelatedTo(type = "HAS_ROLE", direction = Direction.OUTGOING)
	private @Fetch Set<UserRole> roles;

	public User(String username, String password) {
		this(username, password, new HashSet<>(), false);
		// this.addRole("User");
	}

	public User() {
		super();
	}

	public User(String username, String password, Set<UserRole> roles, Boolean isLocked) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.isLocked = isLocked;
	}

	@Override
	public String toString() {
		return "UserNodeDTO [username=" + username + "]";
	}

}
