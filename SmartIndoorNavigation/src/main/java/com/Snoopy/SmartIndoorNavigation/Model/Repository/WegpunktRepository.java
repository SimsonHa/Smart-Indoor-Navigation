package com.Snoopy.SmartIndoorNavigation.Model.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Artikel;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzpunkt;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Wegpunkt;

@Repository
public interface WegpunktRepository extends CrudRepository<Wegpunkt, Long>{
	@Query("SELECT wegpunkt FROM Wegpunkt wegpunkt where wegpunkt.posX = :posX AND wegpunkt.posY = :posY")
	Wegpunkt findByPosition (@Param("posX") double posX, @Param("posY") double posY);
	
}
