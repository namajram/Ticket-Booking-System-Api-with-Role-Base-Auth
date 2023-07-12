package com.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.entites.User;
import com.api.entites.UserDTO;
import com.api.exception.ResourceNotFoundException;
import com.api.repository.UserRepository;
import com.api.services.UserService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Override
	public User createUser(UserDTO userDTO) {
		User user = new User();
		User byEmail = userRepository.findByEmail(userDTO.getEmail());
		if (byEmail==null) {
			user.setEmail(userDTO.getEmail());
			user.setEmail(userDTO.getEmail());
			
			user.setName(userDTO.getName());
			user.setRoleName(userDTO.getRoleName());
		
			user.setPassword(passwordEncoder().encode(userDTO.getPassword()));
		} else {
            throw new ResourceNotFoundException("Email already in use");
		}
		
		

		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User updateUser(long id, UserDTO userDTo) {
		User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Id not found"));

		existingUser.setName(userDTo.getName());

		existingUser.setName(userDTo.getName());

		existingUser.setEmail(userDTo.getEmail());

		existingUser.setPassword(userDTo.getPassword());

		existingUser.setRoleName(userDTo.getRoleName());

	
		return userRepository.save(existingUser);

	}

	@Override
	public boolean deleteUser(long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			userRepository.delete(optionalUser.get());
			return true;
		}
		return false;
	}

	@Override
	public User getUserById(long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser.orElseThrow(() -> new EntityNotFoundException("User Id not found"));
	}

}
