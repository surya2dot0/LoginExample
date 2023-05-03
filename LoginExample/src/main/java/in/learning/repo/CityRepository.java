package in.learning.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.learning.entity.CityMaster;


public interface CityRepository extends JpaRepository<CityMaster, Integer> {
	
	public List<CityMaster> findByStateId(Integer stateId);

}
