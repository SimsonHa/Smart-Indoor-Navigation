package com.Snoopy.SmartIndoorNavigation.Model.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Pi;

public interface PiRepository extends CrudRepository<Pi, Long>{
	List<Pi> findByStatus (boolean status);
}
