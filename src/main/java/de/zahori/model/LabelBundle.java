package de.zahori.model;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints={
    @UniqueConstraint(columnNames = {"name", "locale"})
}) 
public class LabelBundle{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private Locale locale;
	
	@OneToMany
	private List<Label> labels;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public List<Label> getLabels() {
		return labels;
	}

	public void setLabels(List<Label> labels) {
		this.labels = labels;
	}

	@Override
	public String toString() {
		return "LabelBundle [id=" + id + ", name=" + name + ", locale=" + locale + "]";
	}
	
	public Map<String, String> getLabelsAsMap(){
		
		// both ways work 
//		Map<String, String> labelMap = labels.stream().collect(Collectors.toMap(Label::getKey, Label::getValue));
		Map<String, String> labelMap = labels.stream().collect(Collectors.toMap(l -> l.getKey(), l -> l.getValue()));

		return labelMap;
	}
	
	
}
