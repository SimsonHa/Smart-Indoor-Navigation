package com.Snoopy.SmartIndoorNavigation.Model.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.Snoopy.SmartIndoorNavigation.Model.Entity.Grundriss;

@Repository
public interface GrundrissRepository extends CrudRepository<Grundriss, Long> {

}