package com.Snoopy.SmartIndoorNavigation.Model.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Kategorie;

@Repository
public interface KategorieRepository extends CrudRepository<Kategorie, Long> {
	Kategorie findByName(String name);
}
