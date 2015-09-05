package de.mediapool.server.entities.users.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.mediapool.server.core.domain.NodeDTO;
import de.mediapool.server.entities.lists.domain.ListNodeDTO;
import de.mediapool.server.entities.product.domain.ProductNodeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@NodeEntity
@Getter
@Setter
@ToString
public class UserNodeDTO extends NodeDTO {

	private static final long serialVersionUID = 1L;

	private String username;

	@JsonIgnore
	private String password;

	private Boolean isLocked = false;

	@RelatedTo(type = "HAS_ROLE", direction = Direction.OUTGOING)
	private @Fetch Set<UserRoleNodeDTO> roles;

	@RelatedToVia(type = "OWNS", direction = Direction.OUTGOING)
	@Fetch
	private Set<OwnerRelationship> owendProducts;

	@RelatedToVia(type = "FOLLOW", direction = Direction.OUTGOING)
	@Fetch
	private Set<FollowRelationship> followedUser;

	@RelatedTo(type = "CREATED", direction = Direction.OUTGOING)
	@Fetch
	private Set<ListNodeDTO> createdLists;

	public UserNodeDTO(String username, String password) {
		this(username, password, new HashSet<>(), false);
		this.addRole("User");
	}

	public UserNodeDTO() {
		super();
	}

	public UserNodeDTO(String username, String password, Set<UserRoleNodeDTO> roles, Boolean isLocked) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.isLocked = isLocked;
	}

	public boolean createNewList(String title) {
		if (createdLists == null) {
			createdLists = new HashSet<>();
		}
		if (getListByTitle(title) == null) {
			ListNodeDTO list = new ListNodeDTO(title);
			list.setCreated(new Date());
			createdLists.add(list);
			return true;
		}
		return false;
	}

	public ListNodeDTO getListByTitle(String title) {
		ListNodeDTO list = null;

		if (createdLists != null) {
			for (ListNodeDTO lnd : createdLists) {
				if (lnd.getTitle().equals(title)) {
					list = lnd;
				}
			}
		}
		return list;
	}

	public void addRole(String role) {
		if (roles == null) {
			roles = new HashSet<>();
		}
		UserRoleNodeDTO userRole = new UserRoleNodeDTO();
		userRole.setName(role);
		roles.add(userRole);

	}

	public OwnerRelationship owens(ProductNodeDTO product) {
		OwnerRelationship relation = new OwnerRelationship();

		if (owendProducts == null) {
			owendProducts = new HashSet<>();
		}

		relation.setSince(new Date());
		relation.setOwnes(product);
		relation.setUser(this);
		owendProducts.add(relation);

		return relation;
	}

	public FollowRelationship follows(UserNodeDTO user) {
		FollowRelationship relation = new FollowRelationship();

		if (followedUser == null) {
			followedUser = new HashSet<>();
		}

		relation.setSince(new Date());

		relation.setOwnes(user);

		relation.setUser(this);

		followedUser.add(relation);

		return relation;
	}

	@Override
	public String getType() {
		return "USER";
	}

	@Override
	public String toString() {
		return "UserNodeDTO [username=" + username + "]";
	}

}
