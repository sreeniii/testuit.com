package com.testuit.app.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testuit.app.dao.IUserDao;
import com.testuit.app.model.UserRole;

@Service( value = "userService" )
@Transactional
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private IUserDao userDao;
	
	public UserDetails loadUserByUsername(final String username) 
            throws UsernameNotFoundException {

		com.testuit.app.model.User user = userDao.findByUserName(username);
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

		return buildUserForAuthentication(user, authorities);


	}

	// Converts com.testuit.app.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.testuit.app.model.User user, 
		List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), 
			user.getPassword(), user.isActive(), 
                     true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
}
