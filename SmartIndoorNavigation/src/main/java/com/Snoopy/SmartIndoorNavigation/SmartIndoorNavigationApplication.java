package com.Snoopy.SmartIndoorNavigation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Artikel;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.ESL;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Kategorie;
import com.Snoopy.SmartIndoorNavigation.Model.Entity.Pi;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.ArtikelRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.ESLRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.KategorieRepository;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.PiRepository;

@SpringBootApplication
public class SmartIndoorNavigationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartIndoorNavigationApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ArtikelRepository repository1, KategorieRepository repository2, ESLRepository repository3, PiRepository repository4) {
		return (args) -> {
			// save a couple of customers
			
			repository2.save(new Kategorie("Obst"));
			repository2.save(new Kategorie("Gemüse"));
			
			repository1.save(new Artikel("Apfel", 2.5, "123", repository2.findByName("Obst")));
			repository1.save(new Artikel("Tomate", 3.2, "456", repository2.findByName("Gemüse")));
			repository1.save(new Artikel("Kirsche", 4.1, "789", repository2.findByName("Obst")));
			
			
			repository3.save(new ESL(15.5, 500.1));
			repository3.save(new ESL(800.67, 300.6));
			repository3.save(new ESL(100.67, 50.6));
			repository3.save(new ESL(530.7, 333));
			repository3.save(new ESL(1111, 1.1));
			
			Pi p1 =  new Pi("000000003d1d1c36");
			p1.setStatus(true);
			Pi p2 = new Pi("000000003d1d1c66");
			p2.setStatus(true);
			Pi p3 = new Pi("000000003d1d1c21");
			p3.setStatus(true);
			
			repository4.save(p1);
			repository4.save(p2);
			repository4.save(p3);
			repository4.save(new Pi("000000003d1d1c78"));
			
			
		};
	}	
}
