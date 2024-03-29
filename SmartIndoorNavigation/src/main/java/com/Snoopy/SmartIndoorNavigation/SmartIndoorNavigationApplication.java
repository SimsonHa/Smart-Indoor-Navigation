package com.Snoopy.SmartIndoorNavigation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.Snoopy.SmartIndoorNavigation.MQTT.PublishArtikel;
import com.Snoopy.SmartIndoorNavigation.MQTT.SubscribePi;
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
	public CommandLineRunner demo(ArtikelRepository repository1, KategorieRepository repository2, ESLRepository repository3, PiRepository repository4, SubscribePi subscribeService, PublishArtikel publishService) {
		return (args) -> {
			// save a couple of customers
			
			repository2.save(new Kategorie("Obst"));
			repository2.save(new Kategorie("Gemüse"));
			repository2.save(new Kategorie("Getränke"));
			
			repository1.save(new Artikel(33L, "Apfel", 2.5, "123", repository2.findByName("Obst")));
			repository1.save(new Artikel(34L, "Tomate", 5.2, "456", repository2.findByName("Gemüse")));
			repository1.save(new Artikel(35L, "Mango", 4.1, "789", repository2.findByName("Obst")));
			repository1.save(new Artikel(36L, "Bier", 1.1, "444", repository2.findByName("Getränke")));
			

			
			Pi p1 =  new Pi("b827eb4f1802");
			p1.setStatus(true);
			Pi p2 = new Pi("b827eb952596");
			p2.setStatus(true);
			Pi p3 = new Pi("b827eb81d371");
			p3.setStatus(true);
			
			repository4.save(p1);
			repository4.save(p2);
			repository4.save(p3);
//			
//			ESL esl1 = new ESL(560, 240);
//			ESL esl2 = new ESL(502, 205.5);
//			ESL esl3 = new ESL(560, 288);
//			
//			esl1.setPi(p1);
//			esl1.setArtikel(repository1.findByName("Bier"));
//			esl2.setPi(p2);
//			esl2.setArtikel(repository1.findByName("Tomate"));
//			esl3.setPi(p3);
//			esl3.setArtikel(repository1.findByName("Tomate"));
			
			
			//repository3.save(new ESL(3.42, 4));
			//repository3.save(new ESL(3.42, 1.86));
			//repository3.save(new ESL(3.42, 2.9));
			//repository3.save(new ESL(3.42, 3.5));
			//repository3.save(new ESL(3.42, 2.44));
			
			
			
//			repository3.save(esl1);
//			repository3.save(esl2);
//			repository3.save(esl3);
			
//			List<ESL> eslList = (List<ESL>) repository3.findAll();
			
			//Mit Mqtt Broker verbinden
			subscribeService.subscribe();
			
//			Send Artikel für jedes existente ESL Etikett
//			publishService.publish(eslList);
		};
	}	
}
