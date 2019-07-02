package com.Snoopy.SmartIndoorNavigation.Model.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Artikel;

@Repository
public interface ArtikelRepository extends CrudRepository<Artikel, Long> {
	
	Artikel findByName(String name);

	Artikel findByArtNr(String artNr);

}
