package de.zahori;

import java.util.ArrayList;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import de.zahori.model.Label;
import de.zahori.model.LabelBundle;
import de.zahori.model.LabelBundleRepository;
import de.zahori.model.LabelRepository;

/**
 * 
 * To Access the embedded H2 Memory DB:
 * url= http://localhost:8080/h2-console
 * jdbc url= jdbc:h2:mem:testdb
 * username = sa
 * no passwort
 * 
 * 
 * @author zahori
 *
 */
@SpringBootApplication
public class I18nApplication {

	public static void main(String[] args) {
		SpringApplication.run(I18nApplication.class, args);
		
	}
	
	@Autowired
	LabelBundleRepository lbRepo;
	
	
	/**
	 * This will be executed on Startup, to initialize some testdata.
	 * 
	 * @param labelRepository will be injected
	 */
	@Bean
	public ApplicationRunner createDemoData( LabelRepository labelRepository ){
		
		return (args) -> {
			
			// adding some labels
			Locale l = new Locale("de");
			labelRepository.save(new Label(l, "greeting", "Guten Tag!"));
			labelRepository.save(new Label(l, "title", "Mein Label Editor"));
			l = new Locale("en");
			labelRepository.save(new Label(l, "greeting", "Hey Dude!"));
			labelRepository.save(new Label(l, "title", "My Label Editor"));
			
			
			// adding 2 labelBundles
			LabelBundle lb1 = new LabelBundle();
			lb1.setName("default.de");
			lb1.setLocale( new Locale("de"));
			
			ArrayList<Label> labelList = new ArrayList<Label>();
			labelList.add(labelRepository.findOne((long) 1));
			labelList.add(labelRepository.findOne((long) 2));

			lb1.setLabels(labelList);
			
			System.out.println(lb1);
			System.out.println(lb1.getLabels());
			
			lbRepo.saveAndFlush(lb1);
			
			System.out.println("from db:");
			System.out.println("lb.findAll.size = " + lbRepo.findAll().size());
			
			System.out.println("labelList = " + lbRepo.findLabels());
			
			System.out.println("findByName = " + lbRepo.findByName("default.de") );
			System.out.println("findByLocale = " + lbRepo.findByLocale(new Locale("de")) );
			System.out.println("findDistinctLocalesByName = "+lbRepo.findDistinctLocalesForName("default.de"));
			System.out.println("findByNameAndLocale = " + lbRepo.findByNameAndLocale("default.de", new Locale("de")) );
			LabelBundle bundle = lbRepo.findByNameAndLocale("default.de", new Locale("de"));
			System.out.println("Labels = " + bundle.getLabels());
			
			LabelBundle lb2 = new LabelBundle();
			lb2.setName("default.de");
			lb2.setLocale( new Locale("en"));
			ArrayList<Label> labelList2 = new ArrayList<Label>();
			labelList2.add(labelRepository.findOne((long) 3));
			labelList2.add(labelRepository.findOne((long) 4));
			
			lb2.setLabels(labelList2);
			lbRepo.saveAndFlush(lb2);
			
			System.out.println("########################################");
			System.out.println("########################################");
			System.out.println("lb2.findAll.size = " + lbRepo.findAll().size());
			
			System.out.println("labelList2 = " + lbRepo.findLabels());
//			
			System.out.println("findByName = " + lbRepo.findByName("default.de") );
			System.out.println("findByLocale = " + lbRepo.findByLocale(new Locale("en")) );
			System.out.println("findDistinctLocalesByName = "+lbRepo.findDistinctLocalesForName("default.de"));
			System.out.println("findByNameAndLocale = " + lbRepo.findByNameAndLocale("default.de", new Locale("en")) );
			LabelBundle bundle2 = lbRepo.findByNameAndLocale("default.de", new Locale("en"));
			System.out.println("Labels = " + bundle2.getLabels());
			System.out.println("getLabelsAsMap = " + bundle2.getLabelsAsMap());
			
		};
		
	}// end createDemoData
	
}
