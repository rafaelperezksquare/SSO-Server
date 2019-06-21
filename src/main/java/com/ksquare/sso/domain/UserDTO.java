package com.ksquare.sso.domain;

import java.util.List;

public class UserDTO {
	private Long id; 
	
	private String username;
	
	private List<UserRole> roles; 

	UserDTO() { 
	} 

	public UserDTO(User user) { 
		this.id = user.getId();
		this.username = user.getUsername(); 
		this.roles = user.getRoles(); 
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() { 
		return username; 
	} 

	public void setUsername(String username) { 
		this.username = username; 
	} 
	
	public List<UserRole> getRoles() { 
		return roles; 
	} 

	public void setRoles(List<UserRole> roles) { 
		this.roles = roles; 
	} 

}
