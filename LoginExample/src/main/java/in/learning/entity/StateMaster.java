package in.learning.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "STATE_MASTER")
@Data
public class StateMaster {

	@Id
	private Integer stateId;
	
	private Integer countryId;
	private String stateName;
	
}
