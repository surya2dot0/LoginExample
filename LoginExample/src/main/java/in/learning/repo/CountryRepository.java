package in.learning.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.learning.entity.CountryMaster;

public interface CountryRepository extends JpaRepository<CountryMaster, Integer> {
	
}
