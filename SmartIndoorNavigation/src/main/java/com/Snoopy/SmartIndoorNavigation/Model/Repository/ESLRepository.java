package com.Snoopy.SmartIndoorNavigation.Model.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Artikel;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.ESL;


@Repository
public interface ESLRepository extends CrudRepository<ESL, Long> {
	
	@Query("SELECT esl FROM ESL esl where esl.posX = :posX AND esl.posY = :posY")
	ESL findByPosition (@Param("posX") double posX, @Param("posY") double posY);
	
	ESL findByArtikel(Optional<Artikel> a);



}
