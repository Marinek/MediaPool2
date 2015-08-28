package de.mediapool.server.security.domain;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import de.mediapool.server.entities.users.domain.UserNodeDTO;
import de.mediapool.server.entities.users.domain.UserRoleNodeDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MPUserDetails extends UserNodeDTO implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String password;

	private Collection<GrantedAuthority> authorities;
	
	public MPUserDetails(UserNodeDTO userNode) {
		try {
			BeanUtils.copyProperties(this, userNode);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}

		authorities = new ArrayList<GrantedAuthority>();
		
		for(UserRoleNodeDTO userRole : getRoles()) {
			authorities.add(new SimpleGrantedAuthority(userRole.getName()));
		}
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !getIsLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return !getIsLocked();
	}

}
