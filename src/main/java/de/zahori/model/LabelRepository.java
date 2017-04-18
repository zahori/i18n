package de.zahori.model;

import java.util.List;
import java.util.Locale;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {

	List<Label> findByLocale(Locale locale);	
}
