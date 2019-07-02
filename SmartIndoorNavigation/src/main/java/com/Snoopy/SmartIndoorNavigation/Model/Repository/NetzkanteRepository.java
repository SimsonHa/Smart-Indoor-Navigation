package com.Snoopy.SmartIndoorNavigation.Model.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzkante;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Netzpunkt;

@Repository
public interface NetzkanteRepository extends CrudRepository<Netzkante, Long> {
	
	//@Query("SELECT netzkante FROM Netzkante netzkante where netzkante.netzPunkte.get(0) = :netzP OR netzkante.netzPunkte.get(1) = :netzP")
	//List<Netzkante> findConnected (@Param("netzP") Netzpunkt netzP);
}
