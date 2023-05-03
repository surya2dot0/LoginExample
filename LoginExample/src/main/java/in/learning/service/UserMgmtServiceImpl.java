package in.learning.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.learning.binding.LoginForm;
import in.learning.binding.UnLockAccForm;
import in.learning.binding.UserForm;
import in.learning.entity.CityMaster;
import in.learning.entity.CountryMaster;
import in.learning.entity.StateMaster;
import in.learning.entity.User;
import in.learning.repo.CityRepository;
import in.learning.repo.CountryRepository;
import in.learning.repo.StateRepository;
import in.learning.repo.UserRepository;

@Service
public class UserMgmtServiceImpl implements UserMgmtService {

	@Autowired
	UserRepository userRepo;
	@Autowired
	CountryRepository countryRepo;
	
	@Autowired
	StateRepository stateRepo;

	@Autowired
	CityRepository citiRepo;
	
	@Override
	public String checkEmail(String email) {
		
		User user = userRepo.findByEmail(email);
		if(user == null) {
			return "UNIQUE";
		} 
		return "DUPLICATE";
	}

	@Override
	public Map<Integer, String> getCountries() {
		List<CountryMaster> countries = countryRepo.findAll();
		Map<Integer, String> countryMap = new HashMap<>();
		countries.forEach( country -> {
			countryMap.put(country.getCountryId(), country.getCountryName()) ;
				
	});
		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		List<StateMaster> states = stateRepo.findByCountryId(countryId);
		
		Map<Integer, String> stateMap = new HashMap<>();
		states.forEach( state -> {
			stateMap.put(state.getStateId(), state.getStateName()) ;
				
	});
		return stateMap;
	}
	

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		List<CityMaster> cities = citiRepo.findByStateId(stateId);
		
		Map<Integer, String> citiMap = new HashMap<>();
		cities.forEach( city -> {
			citiMap.put(city.getCityId(), city.getCityName()) ;
				
	});
		return citiMap;
	}
	public String generatePassword() {
		
		String text = "ABCDEFJHIJKLMNOPQRSTUVWXYZ0123456789";
		
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		int pwdLength = 6;
		for(int i=1 ; i<=pwdLength;i++) {
			int index = rand.nextInt(text.length());
			sb.append(text.charAt(index));
		}
		return sb.toString();
	}

	@Override
	public String registerUser(UserForm user) {
		User userEntity = new User();
		BeanUtils.copyProperties(user, userEntity);
		userEntity.setAccStatus("LOCKED");
		userEntity.setUserPwd(generatePassword());
		userRepo.save(userEntity);
		//TODO Sent Email to create account
		return "User Account Created";
	}

	@Override
	public String unlockAccount(UnLockAccForm accForm) {
		String email = accForm.getEmail();
		
		User user = userRepo.findByEmail(email);
		if(user.getUserPwd().equals(accForm.getTempPwd())) {
			user.setUserPwd(accForm.getNewPwd());
			user.setAccStatus("UNLOCKED");
			userRepo.save(user);
			return "Account Unlocked";
		}
		return "Invalid Temp Pwd";
	}

	@Override
	public String login(LoginForm loginForm) {
		User user = userRepo.findByEmailAndUserPwd(loginForm.getEmail(), loginForm.getPassword());
		if(user == null) {
			return "Invalid Credentials";
		}
		if(user.getAccStatus().equals("LOCKED")) {
			return "Account Locked";
		}
		return "SUCCESS";
	}

	@Override
	public String forgotPwd(String email) {
		User user = userRepo.findByEmail(email);
		if(user == null) {
			return "Incorrect Email";
		} 
		return user.getUserPwd();
	}

	
}
