package com.ksquare.sso.service;

import java.util.List;

import com.ksquare.sso.domain.User;
import com.ksquare.sso.domain.UserDTO;

public interface UserService {
	List<UserDTO> getUsers();
	UserDTO getUser(Long id);
	UserDTO getUser(String username);
	UserDTO addUser(User user);
	void deleteUser(String username);
	UserDTO updateUser(String username, User user);
	List<String> areUsers(List<String> usernames);
}
