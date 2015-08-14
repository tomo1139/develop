package jp.com.tt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.com.tt.Model.FormLogin;

@Controller
public class Login {
	
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String form(Model model) {
		FormLogin fm = new FormLogin();
		model.addAttribute("formLogin", fm);
		return "Login";
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String form(@ModelAttribute FormLogin fm, Model model) {
		if(fm.getName().equals("aa")) {
			return "todayVisit";
		}
		else if(fm.getName().equals("aaa")) {
			return "error";
		}
		return "Login";
	}
}
