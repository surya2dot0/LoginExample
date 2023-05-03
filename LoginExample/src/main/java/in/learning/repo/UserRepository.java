package in.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.learning.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmail(String email);
	public User findByEmailAndUserPwd(String email, String pwd);
	
}
