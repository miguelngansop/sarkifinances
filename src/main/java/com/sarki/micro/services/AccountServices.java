package com.sarki.micro.services;

import com.sarki.micro.model.AppRole;
import com.sarki.micro.model.AppUser;

public interface AccountServices {

	public AppUser saveUser(AppUser u);
	public AppRole saveRole(AppRole r);
	public AppUser findUserByUsername(String username);
	public void addRoleToUser(String username,String role); 
}
