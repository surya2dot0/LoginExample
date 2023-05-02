package in.learning.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.learning.binding.LoginForm;
import in.learning.binding.UnLockAccForm;
import in.learning.binding.UserForm;
import in.learning.service.UserMgmtService;

@RestController
public class UserRestController {

	@Autowired
	UserMgmtService service;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
		
		String status = service.login(loginForm);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	@PostMapping("/forgotPwd/{email}")
	public ResponseEntity<String> forgotPwd(@PathVariable String email) {
		
		String status = service.forgotPwd(email);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
	
	@PostMapping("/unlockAcc")
	public ResponseEntity<String> unlockAccount(@RequestBody UnLockAccForm form) {
		
		String status = service.unlockAccount(form);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
	
	@PostMapping("/countries")
	public Map<Integer,String> getCountries() {
		
	      return service.getCountries();
	      
	      
	}
	@PostMapping("/countriesResponseEntity")
	public ResponseEntity<Map<Integer,String>> getCountriesRE() {
		
	      Map<Integer, String> countries = service.getCountries();
	      return new ResponseEntity<>(countries,HttpStatus.OK);
	}
	
	
	@PostMapping("/states/{countryId}")
	public Map<Integer,String> getStates(@PathVariable Integer countryId) {
		
	      return service.getStates(countryId);
	}
	
	@PostMapping("/cities/{stateId}")
	public Map<Integer,String> getCities(@PathVariable Integer stateId) {
		
	      return service.getStates(stateId);
	}
	
	@PostMapping("/chkEmail/{email}")
	public String checkEmail(@PathVariable String email) {
		return service.checkEmail(email);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserForm userForm) {
		
		String status  = service.registerUser(userForm);
		return new ResponseEntity<>(status,HttpStatus.CREATED);
	}
}
