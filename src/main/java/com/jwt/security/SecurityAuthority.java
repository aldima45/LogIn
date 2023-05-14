package com.jwt.security;

import org.springframework.security.core.GrantedAuthority;

import com.jwt.models.Authority;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority{
	private static final long serialVersionUID = 3182154365831196334L;
	
	final private Authority authority;
	
	@Override
	public String getAuthority() {
		return authority.getName().toString(); 
	}

}
