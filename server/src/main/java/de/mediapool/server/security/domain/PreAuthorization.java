package de.mediapool.server.security.domain;

public interface PreAuthorization {

	public static final String ROLE_USER = "hasRole('USER')";
	
	public static final String ROLE_ADMIN = "hasRole('ADMIN')";

	public static final String AUTHENTICATED = "isAuthenticated()";

	public static final String ANONYMOUS = "isAnonymous()";
}
