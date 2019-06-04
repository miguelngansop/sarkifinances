package com.sarki.micro.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sarki.micro.model.AppRole;
import com.sarki.micro.model.AppUser;
import com.sarki.micro.repository.AppRoleRepository;
import com.sarki.micro.repository.AppUserRepository;



@Service 
@Transactional
public class AccountServicesImp implements AccountServices{

		
	@Autowired
	private AppUserRepository userRepository;
	@Autowired 
	private AppRoleRepository roleRepository;
	@Autowired 
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public AppUser saveUser(AppUser u) {
		// TODO Auto-generated method stub
		u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
		return userRepository.save(u);
	}

	@Override
	public AppRole saveRole(AppRole r) {
		// TODO Auto-generated method stub
		return roleRepository.save(r);
	}

	@Override
	public AppUser findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		// TODO Auto-generated method stub
		AppUser user=userRepository.findByUsername(username);
		AppRole role=roleRepository.findByRole(roleName);
		user.getRoles().add(role); 
	}

}
