package com.cafe24.mysite5.dto;

import com.cafe24.mysite5.domain.Role;
import com.cafe24.mysite5.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyPrincipal implements UserDetails{
	private User user;

	public MyPrincipal( User user ){
		this.user = user;
	}


	@Override
	public Collection< ? extends GrantedAuthority > getAuthorities(){
		List< GrantedAuthority > authorities = new ArrayList<>();
		for ( Role role : user.getRoles() ) {
			authorities.add( new SimpleGrantedAuthority( role.getName() ) );
		}
		return authorities;
	}

	@Override
	public String getPassword(){
		return user.getPassword();
	}

	@Override
	public String getUsername(){
		return user.getEmail();
	}

	@Override
	public boolean isEnabled(){
		return user.getEnabled().getEnabled();
	}

	@Override
	public boolean isAccountNonExpired(){
		return false;
	}

	@Override
	public boolean isAccountNonLocked(){
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired(){
		return false;
	}

}
