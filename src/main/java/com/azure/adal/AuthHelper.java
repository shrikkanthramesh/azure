
package com.azure.adal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.microsoft.aad.adal4j.AuthenticationResult;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationSuccessResponse;

public final class AuthHelper {

	public static final String PRINCIPAL_SESSION_NAME = "principal";

	private AuthHelper() {
	}

	public static boolean isAuthenticated(HttpServletRequest request) {
		return request.getSession().getAttribute(PRINCIPAL_SESSION_NAME) != null;
	}

	public static AuthenticationResult getAuthSessionObject(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Principal Name"+ auth.getName());
		return (AuthenticationResult) request.getSession().getAttribute(PRINCIPAL_SESSION_NAME);
	}

	public static boolean containsAuthenticationData(HttpServletRequest httpRequest) {
		return httpRequest.getMethod().equalsIgnoreCase("POST")
				&& (httpRequest.getParameterMap().containsKey(AuthParameterNames.ERROR)
						|| httpRequest.getParameterMap().containsKey(AuthParameterNames.ID_TOKEN)
						|| httpRequest.getParameterMap().containsKey(AuthParameterNames.CODE));
	}

	public static boolean isAuthenticationSuccessful(AuthenticationResponse authResponse) {
		return authResponse instanceof AuthenticationSuccessResponse;
	}
}
