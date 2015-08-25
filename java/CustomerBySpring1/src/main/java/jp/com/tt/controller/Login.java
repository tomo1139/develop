package jp.com.tt.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.WebRequest;

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
import jp.com.tt.model.MyUtils;
import jp.com.tt.model.Negotiation;
import jp.com.tt.model.NegotiationDao;
import jp.com.tt.model.NegotiationDaoImpl;
import jp.com.tt.model.Today;
import jp.com.tt.model.TodayDao;
import jp.com.tt.model.TodayDaoImpl;
import jp.com.tt.model.TodayPrintData;
import jp.com.tt.model.User;
import jp.com.tt.model.UserDao;
import jp.com.tt.model.UserDaoImpl;

@Controller
public class Login {
	
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String form(Model model) {
		FormLogin fm = new FormLogin();
		model.addAttribute("formLogin", fm);
		
		//addAllDB(model);
		
		return "Login";
	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String form(@ModelAttribute FormLogin fm, Model model, HttpSession session) throws NoSuchAlgorithmException {
		/*
		String hexString = DigestUtils.md5Hex("aaaa"); 
		System.out.println(hexString);
		*/

		UserDao<User> dao = new UserDaoImpl();
		List<User> list = dao.getAll();
		
		for(User user : list) {
			
			String fmMd5pass = DigestUtils.md5Hex(fm.getPass());

			if(user.getName().equals(fm.getName()) &&
			   user.getPass().equals(fmMd5pass)) {
				
				session.setAttribute("loginUser", user);

				return "redirect:TodayVisit";
			}
		}

		model.addAttribute("loginErrorMsg", "有効なアカウント情報でログインして下さい");
		return "Login";
	}
	
	private void addAllDB(Model model) {
		{
			UserDao<User> dao = new UserDaoImpl();
			List<User> list = dao.getAll();
			model.addAttribute("userlist", list);
		}

		{
			CustomerDao<Customer> dao = new CustomerDaoImpl();
			List<Customer> list = dao.getAll();
			model.addAttribute("customerlist", list);
		}
		
		{
			NegotiationDao<Negotiation> dao = new NegotiationDaoImpl();
			List<Negotiation> list = dao.getAll();
			model.addAttribute("negotiationlist", list);
		}
		
		{
			TodayDao<Today> dao = new TodayDaoImpl();
			List<Today> list = dao.getAll();
			model.addAttribute("todaylist", list);
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
	}
}
