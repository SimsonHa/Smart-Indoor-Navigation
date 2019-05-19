package com.Snoopy.SmartIndoorNavigation.Model.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Wegpunkt;

@Repository
public interface WegpunktRepository extends CrudRepository<Wegpunkt, Long>{

}
