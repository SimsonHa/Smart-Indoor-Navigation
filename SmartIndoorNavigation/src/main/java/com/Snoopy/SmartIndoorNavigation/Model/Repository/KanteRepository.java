package com.Snoopy.SmartIndoorNavigation.Model.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Kante;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Wegpunkt;

@Repository
public interface KanteRepository extends CrudRepository<Kante, Long> {
	

}
