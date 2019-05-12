package com.Snoopy.SmartIndoorNavigation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.Snoopy.SmartIndoorNavigation.Model.Entity.Product;
import com.Snoopy.SmartIndoorNavigation.Model.Repository.ProductRepository;

@SpringBootApplication
public class SmartIndoorNavigationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartIndoorNavigationApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ProductRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Product("Apfel", 2, "123"));
			repository.save(new Product("Tomate", 3, "456"));
			repository.save(new Product("Kirsche", 4, "789"));
		};
	}	
}
