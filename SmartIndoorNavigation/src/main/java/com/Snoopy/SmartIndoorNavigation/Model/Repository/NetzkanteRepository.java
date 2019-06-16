package com.Snoopy.SmartIndoorNavigation.Model.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzkante;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzpunkt;

@Repository
public interface NetzkanteRepository extends CrudRepository<Netzkante, Long> {
	

}
