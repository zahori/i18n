package de.zahori.model;

import java.util.List;
import java.util.Locale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LabelRepository extends JpaRepository<Label, Long> {

	List<Label> findByLocale(Locale locale);	
}
