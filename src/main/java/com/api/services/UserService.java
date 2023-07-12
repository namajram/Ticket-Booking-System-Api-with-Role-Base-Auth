package com.api.services;

import java.util.List;

import com.api.entites.User;
import com.api.entites.UserDTO;

public interface UserService {
	User createUser(UserDTO userDTO);
	User updateUser(long id, UserDTO userDTO);
	List<User> getAllUsers();
	boolean deleteUser(long id);
	User getUserById(long id);
}
