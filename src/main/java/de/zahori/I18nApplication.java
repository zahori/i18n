package de.zahori;

import java.util.Locale;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import de.zahori.model.Label;
import de.zahori.model.LabelRepository;

@SpringBootApplication
public class I18nApplication {

	public static void main(String[] args) {
		SpringApplication.run(I18nApplication.class, args);
		
	}
	
	/**
	 * This will be executed on Startup, to initialize some testdata.
	 * 
	 * @param labelRepository will be injected
	 */
	@Bean
	public ApplicationRunner createDemoData( LabelRepository labelRepository ){
		
		return (args) -> {
			
			Locale l = new Locale("de");
			labelRepository.save(new Label(l, "greeting", "Guten Tag!"));
			labelRepository.save(new Label(l, "title", "Mein Label Editor"));
			l = new Locale("en");
			labelRepository.save(new Label(l, "greeting", "Hey Dude!"));
			labelRepository.save(new Label(l, "title", "My Label Editor"));
		};
		
	}// end createDemoData
	
}
