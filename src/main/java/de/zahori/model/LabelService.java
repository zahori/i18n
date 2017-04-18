package de.zahori.model;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelService {

	@Autowired
	LabelRepository repo;
	
	public Map<String, String> getLabelBundle(Locale locale){
	
		Map<String, String> bundle = new HashMap<String, String>();
		List<Label> labelList = repo.findByLocale(locale);
		
		for (Label label : labelList) {
			bundle.put(label.getKey(), label.getValue());
		}
		
		
		return bundle;
		
	}
	
	
}
