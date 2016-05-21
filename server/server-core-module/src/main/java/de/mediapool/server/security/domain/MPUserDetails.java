package de.mediapool.server.security.domain;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import de.mediapool.server.entities.users.domain.User;
import de.mediapool.server.entities.users.domain.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MPUserDetails extends User implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String password;

	private Collection<GrantedAuthority> authorities;

	public MPUserDetails(User userNode) {
		try {
			BeanUtils.copyProperties(this, userNode);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}

		authorities = new ArrayList<GrantedAuthority>();

		for (UserRole userRole : getRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getTitle()));
		}
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
