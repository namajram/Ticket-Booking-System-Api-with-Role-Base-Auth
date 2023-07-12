package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.entites.AuthRequest;
import com.api.entites.User;
import com.api.entites.UserDTO;
import com.api.services.impl.JwtService;
import com.api.services.impl.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	 private UserServiceImpl userServiceImpl;
	
	
	@GetMapping("/login")
	public ResponseEntity<?> authenticateAndGetToken(@Valid @RequestBody AuthRequest authRequest) {
		try {  
			Authentication authentication = authenticationManager.authenticate(
		
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
		String generateToken = jwtService.generateToken(authRequest.getEmail());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(generateToken);
		} else {
			throw new UsernameNotFoundException("invalid email ");}
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid email or password");
			}
		}
	
	
	 @PostMapping("/register")
	    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
			try {   
		 User createdUser = userServiceImpl.createUser(userDTO);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user");
			}
	    }
	 
	  @GetMapping("/get/all")
	  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	    public ResponseEntity<?> getAllUsers() {
		  try {
			  List<User> users = userServiceImpl.getAllUsers();
		  
	        return ResponseEntity.ok(users);
	  } catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	    }
	  
	  @PutMapping("/update/{id}")
	 
	    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody UserDTO userDTo) {
		  try {
			  User updatedUser = userServiceImpl.updateUser(id, userDTo);
	        if (updatedUser != null) {
	            return ResponseEntity.ok(updatedUser);
	        }
	        return ResponseEntity.notFound().build();
	  } catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
		    }
	    
	  
	  @DeleteMapping("/delete/{id}")
	  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	    public ResponseEntity<?> deleteUser(@PathVariable long id) {
		  try {
	        boolean deleted = userServiceImpl.deleteUser(id);
	        if (deleted) {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("successfully deleted");
	        }
	        return ResponseEntity.notFound().build();
		  } catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
		    }
	  
	  @GetMapping("/get/{id}")
	     public ResponseEntity<?> getUserById(@PathVariable long id) {
		  try {
	        User user = userServiceImpl.getUserById(id);
	        if (user != null) {
	            return ResponseEntity.ok(user);
	        }
	        return ResponseEntity.notFound().build();
		  } catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
		    }
}
