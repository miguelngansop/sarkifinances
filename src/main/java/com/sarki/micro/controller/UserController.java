package com.sarki.micro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sarki.micro.model.AppUser;
import com.sarki.micro.model.Client;
import com.sarki.micro.objects.RegistrationForm;
import com.sarki.micro.services.AccountServicesImp;


@RestController
@RequestMapping("/api2")
public class UserController {
	@Autowired
	private AccountServicesImp accountService;
	
	@PostMapping("/test")
	public AppUser signUp(@RequestBody RegistrationForm data) {
		String username=data.getUsername(); 
		AppUser user=accountService.findUserByUsername(username);
		if(user!=null) throw new RuntimeException("Cet utilisateur existe déjà, essayez avec un autre nom d'utilisateur"); 
		String password=data.getPassword();
		String repassword=data.getRepassword();
		if(!password.equals(repassword)) throw new RuntimeException("Vous devez confirmer votre mot de passe");
		AppUser u=new AppUser();
		u.setUsername(username);
		u.setPassword(password);
		u.setClient(null);
		u.setEmail(data.getEmail());
		accountService.saveUser(u);
		accountService.addRoleToUser(username, "Admin");
		return (u);
	}
	
	 @GetMapping("/authenticate")
	  @ResponseStatus(HttpStatus.NO_CONTENT)
	  public void authenticate() {
	    // we don't have to do anything here
	    // this is just a secure endpoint and the JWTFilter
	    // validates the token
	    // this service is called at startup of the app to check
	    // if the jwt token is still valid
	  }

}
