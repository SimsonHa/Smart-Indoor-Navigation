package com.Snoopy.SmartIndoorNavigation.Model.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Pi;

@Repository
public interface PiRepository extends CrudRepository<Pi, Long>{
	
	List<Pi> findByStatus (boolean status);
	
	Pi findByMacAdress (String macAdress);
}
