package jp.com.tt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.com.tt.Model.FormLogin;

@Controller
public class AddTodayVisit {
	
	@RequestMapping(value = "/AddTodayVisit", method = RequestMethod.GET)
	public String form(Model model) {
		return "addTodayVisit";
	}
	
	@RequestMapping(value = "/AddTodayVisit", method = RequestMethod.POST)
	public String form(@ModelAttribute FormLogin fm, Model model) {
		return "addTodayVisit";
	}
}