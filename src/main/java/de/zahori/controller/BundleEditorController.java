package de.zahori.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import de.zahori.model.LabelRepository;
import de.zahori.model.LabelService;

@Controller
public class BundleEditorController {

	@Autowired
	LabelService labelService;
	
	@Autowired
	LabelRepository repo;
	
	@RequestMapping(value="/")
	public String index(Model model){
//		model.addAllAttributes(labelService.getLabelBundle( new Locale("de") ));
//		model.addAttribute("labels", labelService.getLabelBundle( new Locale("de") ));
		Locale locale = new Locale("de");
		model.addAttribute("locale", locale);
		model.addAttribute("labels", repo.findByLocale(locale));
		return "index";
	}
	
}
