package in.learning.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.learning.entity.StateMaster;

public interface StateRepository extends JpaRepository<StateMaster, Integer> {

	public List<StateMaster> findByCountryId(Integer countryId);
	
}

