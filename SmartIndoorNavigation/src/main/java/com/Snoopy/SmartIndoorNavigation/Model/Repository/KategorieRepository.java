package com.Snoopy.SmartIndoorNavigation.Model.Repository;

import org.springframework.data.repository.CrudRepository;


import com.Snoopy.SmartIndoorNavigation.Model.Entity.Kategorie;


public interface KategorieRepository extends CrudRepository<Kategorie, Long> {
	Kategorie findByName(String name);
}
