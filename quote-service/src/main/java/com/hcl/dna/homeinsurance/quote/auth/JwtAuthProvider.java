package com.hcl.dna.homeinsurance.quote.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Object userObj = authentication.getPrincipal();
        if (userObj != null) {
        	User user = (User)userObj;
        	if(user != null && user.getUsername() != null && !"".equals(user.getUsername()))	{
                Authentication newAuthentication = new PreAuthenticatedAuthenticationToken(user, "");
                newAuthentication.setAuthenticated(true);
                return newAuthentication;
        	}
        }
        throw new BadCredentialsException("Bad credentials given.");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(PreAuthenticatedAuthenticationToken.class);
	}

}
