package jp.com.tt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.com.tt.model.Customer;
import jp.com.tt.model.FormLogin;
import jp.com.tt.model.MyUtils;
import jp.com.tt.model.Today;
import jp.com.tt.model.TodayDao;
import jp.com.tt.model.TodayDaoImpl;
import jp.com.tt.model.TodayPrintData;
import jp.com.tt.model.User;

@Controller
public class TodayVisit {
	
	@RequestMapping(value = "/TodayVisit", method = RequestMethod.GET)
	public String form(Model model, HttpSession session) {
		System.out.println("TodayVisit.java form()");

		session.removeAttribute("searchList");
		User user = (User) session.getAttribute("loginUser");
		model.addAttribute("printTodayList", MyUtils.getPrintTodayList(user));
		model.addAttribute("printTodayUnregList", MyUtils.getPrintTodayUnregList(user));

		return "todayVisit";
	}
	
	@RequestMapping(value = "/TodayVisit", method = RequestMethod.POST)
	public String formPost(Model model, HttpSession session) {
		System.out.println("TodayVisit.java formPost()");

		User user = (User) session.getAttribute("loginUser");
		model.addAttribute("printTodayList", MyUtils.getPrintTodayList(user));
		model.addAttribute("printTodayUnregList", MyUtils.getPrintTodayUnregList(user));
		return "todayVisit";
	}
	
	@RequestMapping(value = "/TodayDelete", method = RequestMethod.POST)
	public String form(@ModelAttribute FormLogin fm, Model model, HttpSession session, HttpServletRequest request) {
		System.out.println("TodayVisit.java form()");

		String deleteTodayId = (String)request.getParameter("deleteToday");

		TodayDao<Today> dao = new TodayDaoImpl();
		dao.delete(Integer.parseInt(deleteTodayId));

		User user = (User) session.getAttribute("loginUser");

		model.addAttribute("printTodayList", MyUtils.getPrintTodayList(user));
		model.addAttribute("printTodayUnregList", MyUtils.getPrintTodayUnregList(user));

		return "todayVisit";
	}
}