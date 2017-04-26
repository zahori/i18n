package de.zahori.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import de.zahori.model.LabelRepository;
import de.zahori.model.LabelService;

@Controller
@RequestMapping(value="/site")
public class BundleEditorController {

	@SuppressWarnings("unused")
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	LabelService labelService;
	
	@Autowired
	LabelRepository repo;
	
	
	
	@RequestMapping
	public String index(Model model){
		
//		model.addAllAttributes(labelService.getLabelBundle( new Locale("de") ));
//		model.addAttribute("labels", labelService.getLabelBundle( new Locale("de") ));
		
		return "redirect:/site/" + "de";
	}
	
	@RequestMapping(path="/{lang}")
	public String indexWithLocale(Model model, @PathVariable(value="lang") String lang){
		
		Locale locale = new Locale(lang);
		model.addAttribute("locale", locale);
		model.addAttribute("availableLocales", repo.findDistinctLocales());
		model.addAttribute("labels", repo.findByLocale(locale));
		return "index";
	}
	
	
}
