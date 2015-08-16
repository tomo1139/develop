package jp.com.tt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.com.tt.model.FormLogin;
import jp.com.tt.model.User;
import jp.com.tt.model.UserDao;
import jp.com.tt.model.UserDaoImpl;

@Controller
public class Login {
	
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String form(Model model) {
		FormLogin fm = new FormLogin();
		model.addAttribute("formLogin", fm);
		
		UserDao<User> dao = new UserDaoImpl();
		List<User> list = dao.getAll();
		model.addAttribute("userlist", list);
		
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
