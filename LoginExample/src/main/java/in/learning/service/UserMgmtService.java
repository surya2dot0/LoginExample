package in.learning.service;

import java.util.Map;

import in.learning.binding.LoginForm;
import in.learning.binding.UnLockAccForm;
import in.learning.binding.UserForm;

public interface UserMgmtService {

	public String checkEmail(String email);
	public Map<Integer,String> getCountries();
	public Map<Integer,String> getStates(Integer countryId);
	public Map<Integer,String> getCities(Integer stateId);
	public String registerUser(UserForm user);
	public String unlockAccount(UnLockAccForm accForm);
	public String login(LoginForm loginForm);
	public String forgotPwd(String email);
}
