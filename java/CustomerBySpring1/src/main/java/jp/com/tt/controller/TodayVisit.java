package jp.com.tt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.com.tt.model.beans.Today;
import jp.com.tt.model.beans.User;
import jp.com.tt.model.dao.MyException;
import jp.com.tt.model.dao.TodayDao;
import jp.com.tt.model.dao.TodayDaoImpl;
import jp.com.tt.model.form.FormLogin;
import jp.com.tt.model.util.MyUtils;

@Controller
public class TodayVisit {
	
	@RequestMapping(value = "/TodayVisit", method = RequestMethod.GET)
	public String form(Model model, HttpSession session) {

		session.removeAttribute("searchList");
		session.removeAttribute("pageInfo");
		session.removeAttribute("oldFormData");

		User user = (User) session.getAttribute("loginUser");
		model.addAttribute("printTodayList", MyUtils.getPrintTodayList(user));
		model.addAttribute("printTodayUnregList", MyUtils.getPrintTodayUnregList(user));

		return "todayVisit";
	}
	
	@RequestMapping(value = "/TodayVisit", method = RequestMethod.POST)
	public String formPost(Model model, HttpSession session) {

		User user = (User) session.getAttribute("loginUser");
		model.addAttribute("printTodayList", MyUtils.getPrintTodayList(user));
		model.addAttribute("printTodayUnregList", MyUtils.getPrintTodayUnregList(user));
		return "todayVisit";
	}
	
	@RequestMapping(value = "/TodayDelete", method = RequestMethod.POST)
	public String form(@ModelAttribute FormLogin fm, Model model, HttpSession session, HttpServletRequest request) {

		try {
			String deleteTodayId = (String)request.getParameter("deleteToday");

			TodayDao<Today> dao = new TodayDaoImpl();
			dao.delete(Integer.parseInt(deleteTodayId));
		} catch (MyException e) {
			;
		}

		User user = (User) session.getAttribute("loginUser");
		model.addAttribute("printTodayList", MyUtils.getPrintTodayList(user));
		model.addAttribute("printTodayUnregList", MyUtils.getPrintTodayUnregList(user));

		return "todayVisit";
	}
}