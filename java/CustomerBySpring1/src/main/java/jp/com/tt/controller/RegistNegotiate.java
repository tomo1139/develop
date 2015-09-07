package jp.com.tt.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.com.tt.model.beans.Customer;
import jp.com.tt.model.beans.CustomerMeans;
import jp.com.tt.model.beans.CustomerOpponent;
import jp.com.tt.model.beans.CustomerStatus;
import jp.com.tt.model.beans.Today;
import jp.com.tt.model.beans.User;
import jp.com.tt.model.dao.CustomerDao;
import jp.com.tt.model.dao.CustomerDaoImpl;
import jp.com.tt.model.dao.CustomerMeansDao;
import jp.com.tt.model.dao.CustomerMeansDaoImpl;
import jp.com.tt.model.dao.CustomerOpponentDao;
import jp.com.tt.model.dao.CustomerOpponentDaoImpl;
import jp.com.tt.model.dao.CustomerStatusDao;
import jp.com.tt.model.dao.CustomerStatusDaoImpl;
import jp.com.tt.model.dao.MyException;
import jp.com.tt.model.dao.TodayDao;
import jp.com.tt.model.dao.TodayDaoImpl;
import jp.com.tt.model.form.FormEdit;
import jp.com.tt.model.form.FormRegist;
import jp.com.tt.model.util.Counter;
import jp.com.tt.model.util.MyUtils;

@Controller
public class RegistNegotiate {
	
	@RequestMapping(value = "/RegistNegotiate", method = RequestMethod.GET)
	public String formGet(Model model) {
		return "registNegotiate";
	}
	
	@RequestMapping(produces="text/plain;charset=UTF-8", value = "/RegistNegotiate", method = RequestMethod.POST)
	public String formPost(@ModelAttribute FormRegist fm, Model model,  HttpSession session, HttpServletRequest request) throws ParseException, NumberFormatException, MyException {
		
		{
			String id = request.getParameter("todayId");
			TodayDao<Today> dao = new TodayDaoImpl();
			Today data = dao.findById(Integer.parseInt(id));
			model.addAttribute("today", data);
		}

		{
			String id = request.getParameter("id");
			CustomerDao<Customer> dao = new CustomerDaoImpl();
			Customer cust = null;
			try {
				cust = dao.findById(Integer.parseInt(id));
			} catch (MyException e) {
				return "error";
			}
			model.addAttribute("customer", cust);
			model.addAttribute("contractTerm", MyUtils.getContractTerm(cust.getContract_date()));
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

	@RequestMapping(produces="text/plain;charset=UTF-8", value = "/RegistNegotiateEdit", method = RequestMethod.POST)
	public String formPostEdit(@ModelAttribute FormEdit fm, Model model,  HttpSession session, HttpServletRequest request) throws ParseException {
		User user = (User) session.getAttribute("loginUser");
		if(user == null) { return "redirect: Login"; }
		
		{
			String id = request.getParameter("id");
			CustomerDao<Customer> dao = new CustomerDaoImpl();
			Customer cust = null;
			try {
				cust = dao.findById(Integer.parseInt(id));
			} catch (MyException e) {
				return "error";
			}
			model.addAttribute("customer", cust);
			model.addAttribute("contractTerm", MyUtils.getContractTerm(cust.getContract_date()));
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
		
		//String id       = request.getParameter("id");
		String date     = request.getParameter("date");
		date = date.replaceAll("-", "/");
		String time     = request.getParameter("time");

		String method   = request.getParameter("method");
		String opponent = request.getParameter("opponent");
		String result   = request.getParameter("result");
		String negoId   = request.getParameter("negoId");

		String detailEdit = request.getParameter("detail");
		detailEdit = detailEdit.replaceAll("<br>", "");

        model.addAttribute("datetime", date+time);
        model.addAttribute("method", method);
        model.addAttribute("opponent", opponent);
        model.addAttribute("result", result);
        model.addAttribute("negoId", negoId);
        
        fm.setDetailEdit(detailEdit);
        model.addAttribute("detailEdit", detailEdit);
        
        model.addAttribute("count", Counter.get());
		
		return "registNegotiateEdit";
	}
}
