package de.zahori.model;

import java.util.List;
import java.util.Locale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LabelBundleRepository extends JpaRepository<LabelBundle, Long> {


		List<LabelBundle> findByLocale(Locale locale);	
		List<LabelBundle> findByName(String name);
		
		// had to use a query here to enable eager loading by using join fetch 
		@Query("select lb from LabelBundle lb join fetch lb.labels where lb.name=:name and lb.locale=:locale")
		LabelBundle	findByNameAndLocale(@Param("name") String name, @Param("locale") Locale locale);
		
		@Query("select lb.labels from LabelBundle lb")
		List<Label> findLabels(); // shows all existing labels independend to their LabelBundle
		
		@Query("select distinct lb.locale from LabelBundle lb where lb.name=:name")
		List<Locale> findDistinctLocalesForName(@Param("name") String name);
		
		
}
