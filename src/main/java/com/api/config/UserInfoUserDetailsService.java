package com.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.repository.UserRepository;
@Service
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		com.api.entites.User findByEmail = userRepository.findByEmail(username);
	       if (findByEmail == null) {
	           throw new UsernameNotFoundException("User not found");
	       }
	       return new org.springframework.security.core.userdetails.User(
	                findByEmail.getEmail(),
	                findByEmail.getPassword(),
	                AuthorityUtils.commaSeparatedStringToAuthorityList(findByEmail.getRoleName().toString()));
	}

}
