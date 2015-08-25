package jp.com.tt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.com.tt.model.Counter;
import jp.com.tt.model.Customer;
import jp.com.tt.model.CustomerDao;
import jp.com.tt.model.CustomerDaoImpl;
import jp.com.tt.model.CustomerMeans;
import jp.com.tt.model.CustomerMeansDao;
import jp.com.tt.model.CustomerMeansDaoImpl;
import jp.com.tt.model.CustomerOpponent;
import jp.com.tt.model.CustomerOpponentDao;
import jp.com.tt.model.CustomerOpponentDaoImpl;
import jp.com.tt.model.CustomerStatus;
import jp.com.tt.model.CustomerStatusDao;
import jp.com.tt.model.CustomerStatusDaoImpl;
import jp.com.tt.model.FormLogin;
import jp.com.tt.model.FormRegist;
import jp.com.tt.model.Today;
import jp.com.tt.model.TodayDao;
import jp.com.tt.model.TodayDaoImpl;

@Controller
public class RegistNegotiate {
	
	@RequestMapping(value = "/RegistNegotiate", method = RequestMethod.GET)
	public String formGet(Model model) {
		return "registNegotiate";
	}
	
	@RequestMapping(produces="text/plain;charset=UTF-8", value = "/RegistNegotiate", method = RequestMethod.POST)
	public String formPost(@ModelAttribute FormRegist fm, Model model,  HttpSession session, HttpServletRequest request) {
		
		{
			String id = request.getParameter("todayId");
			TodayDao<Today> dao = new TodayDaoImpl();
			Today data = dao.findById(Integer.parseInt(id));
			model.addAttribute("today", data);
		}

		{
			String id = request.getParameter("id");
			CustomerDao<Customer> dao = new CustomerDaoImpl();
			Customer cust = dao.findById(Integer.parseInt(id));
			model.addAttribute("customer", cust);
		}

		{
			CustomerMeansDao<CustomerMeans> dao = new CustomerMeansDaoImpl();
			List<CustomerMeans> list = dao.getAll();
			model.addAttribute("customerMeanslist", list);
		}
		
		{
			CustomerOpponentDao<CustomerOpponent> dao = new CustomerOpponentDaoImpl();
			List<CustomerOpponent> list = dao.getAll();
			model.addAttribute("customerOpponentlist", list);
		}
		
		{
			CustomerStatusDao<CustomerStatus> dao = new CustomerStatusDaoImpl();
			List<CustomerStatus> list = dao.getAll();
			model.addAttribute("customerStatuslist", list);
		}
		
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        model.addAttribute("nowdatetime", sdf.format(date));
        
        model.addAttribute("count", Counter.get());
		
		return "registNegotiate";
	}

	/*
	@RequestMapping(value = "/RegistNegotiate", method = RequestMethod.POST)
	public String formPost(Model model) {
		return "registNegotiate";
	}
	*/
}