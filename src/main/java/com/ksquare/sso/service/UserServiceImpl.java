package com.ksquare.sso.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.ksquare.sso.domain.User;
import com.ksquare.sso.domain.UserDTO;
import com.ksquare.sso.domain.UserRole;
import com.ksquare.sso.repository.UserRepository;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public List<UserDTO> getUsers() {
		ArrayList<User> users = Lists.newArrayList(userRepository.findAll());
		ArrayList<UserDTO> usersDTO = new ArrayList<UserDTO>();
		for(User user : users) {
			usersDTO.add(new UserDTO(user));
		}
		return usersDTO;
	}
	
	@Override
	public UserDTO getUser(Long id) {
		return new UserDTO(userRepository.findOne(id));
	}

	@Override
	public UserDTO getUser(String username) {
		return new UserDTO(userRepository.findByUsername(username));
	}

	@Override
	public UserDTO addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return new UserDTO(user);
	}

	@Override
	public void deleteUser(String username) {
		userRepository.delete(userRepository.findByUsername(username));		
	}

	@Override
	public UserDTO updateUser(String username, User user) {
		User userToUpdate = userRepository.findByUsername(username);
		if(user.getUsername() != null) {
			userToUpdate.setUsername(user.getUsername());
		}
		if(user.getPassword() != null) {
			userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		if(user.getRoles() != null) {
			userToUpdate.setRoles(user.getRoles());
		}
		return new UserDTO(userRepository.save(userToUpdate));
	}
	
	@Override
	public List<String> areUsers(List<String> usernames){
		List<User> existingUsers = Lists.newArrayList(userRepository.findAll());
		for (User user : existingUsers) {
			if(usernames.contains(user.getUsername())) {
				usernames.remove(user.getUsername());
			}
		}
		return usernames;
	}
	
	@PostConstruct
	private void setupDefaultUser() {
		if (userRepository.count() == 0) {
			userRepository.save(new User(
					"crmadmin", 
					passwordEncoder.encode("adminpass"), 
					Arrays.asList(
							new UserRole("USER"), 
							new UserRole("ADMIN"))));
		}		
	}
	
}
