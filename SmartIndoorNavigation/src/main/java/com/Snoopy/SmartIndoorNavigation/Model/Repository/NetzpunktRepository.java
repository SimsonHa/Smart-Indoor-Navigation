package com.Snoopy.SmartIndoorNavigation.Model.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Artikel;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzpunkt;


@Repository
public interface NetzpunktRepository extends CrudRepository<Netzpunkt, Long> {
	@Query("SELECT netzpunkt FROM Netzpunkt netzpunkt where netzpunkt.posX = :posX AND netzpunkt.posY = :posY")
	Netzpunkt findByPosition (@Param("posX") double posX, @Param("posY") double posY);
	
	Netzpunkt findByArtikel(Artikel artikel);
	Netzpunkt findByStatus(String status);
}
