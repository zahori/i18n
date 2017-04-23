package de.zahori.model;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/label")
public class LabelService {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LabelRepository repo;
	
	@RequestMapping(method=RequestMethod.GET)
	public Map<String, String> getLabelBundle( Optional<Locale> pLocale){
	
		Locale locale = pLocale.orElse(new Locale("de"));
		
		Map<String, String> bundle = new HashMap<String, String>();
		List<Label> labelList = repo.findByLocale(locale);
		
		for (Label label : labelList) {
			bundle.put(label.getKey(), label.getValue());
		}
		
		return bundle;
	}
	
	/**
	 * returns all labels in the given language.
	 * eg: /label/en
	 * 
	 * @param language
	 * @return
	 */
	@RequestMapping(path = "/{lang}", method=RequestMethod.GET)
	public Map<String, String> getLabelBundleByLanguage( @PathVariable(value="lang") String language ){
		
		LOG.info("getLabelBundleByLanguage :: Start");
		LOG.info("getLabelBundleByLanguage:: language = " + language);
		
		Locale locale = new Locale(language);
		
		LOG.info("getLabelBundleByLanguage:: End with locale = " + locale);
		return getLabelBundle( Optional.of(locale) );
	}
	
	/**
	 * Returns an array with all used locales (distinct)
	 * 
	 * @return
	 */
	@RequestMapping(path = "/locales", method=RequestMethod.GET)
	public List<Locale> getLocales( ){
		return repo.findDistinctLocales();
	}
	
	
	/**
	 * 
	 * Update a Label.
	 * ID is given by a PathVariable eg. /label/2
	 * The value is transmitted by the request body 
	 * 
	 * @param labelId
	 * @param data
	 * @return
	 */
	@RequestMapping(path = "/{labelId}", method=RequestMethod.POST)
	public Label setLabel(@PathVariable(name="labelId") Long labelId, @RequestBody String data ){
		
		System.out.println("setLabel:: start with ID = " + labelId);
		System.out.println("setLabel:: Data = " + data);
		
		Label label = repo.findOne(labelId);
		label.setValue(data);
		repo.saveAndFlush(label);
		
		return label;
	}
	
	
	//TODO: Untested - implement this or something similar
	@RequestMapping(path = "/", method=RequestMethod.PUT)
	public Label createNewLabel( String key, String value ){
		
		System.out.println("setLabel:: start");
		System.out.println("key: " + key);
		System.out.println("value: " + value);
		
		Label label =  new Label();
		label.setKey(key);
		label.setValue(value);
		repo.saveAndFlush(label);
		
		return label;
	}
	
	
}
