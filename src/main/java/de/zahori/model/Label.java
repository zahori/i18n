package de.zahori.model;


import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Label {

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Long id;
	
	private Locale locale;
	
	private String key;
	private String value;
	
	public Label(Locale locale, String key, String value){
		this.locale=locale;
		this.key = key;
		this.value = value;
	}
	
	public Label(){
		
	}
	
	public String toString(){
		return "[" + id + ":" + locale + ":" + key + ":" + value +"]"; 
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getId() {
		return id;
	}
	
}
