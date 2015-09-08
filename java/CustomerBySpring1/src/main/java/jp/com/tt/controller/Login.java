package jp.com.tt.controller;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.com.tt.model.beans.Today;
import jp.com.tt.model.beans.User;
import jp.com.tt.model.dao.TodayDao;
import jp.com.tt.model.dao.TodayDaoImpl;
import jp.com.tt.model.dao.UserDao;
import jp.com.tt.model.dao.UserDaoImpl;
import jp.com.tt.model.form.FormLogin;

@Controller
public class Login {
	
	private static String executedDate = "";
	
	private void deleteUselessTodayData() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
        String nowDateTime = sdf.format(date);
        
        if(executedDate.startsWith(nowDateTime.substring(0, 10))) {
        	// Do Nothing...
        } else {
        	
        	executedDate = nowDateTime;

        	TodayDao<Today> dao = new TodayDaoImpl();
			List<Today> list = dao.getNotSameDay(nowDateTime.substring(0, 10));

			for(Today today : list) {
				if(today.getM_flg() == 1) {
					dao.delete(today);
				}
			}
        }
	}
	
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String form(Model model, HttpSession session) {
		System.out.println("GET");
		FormLogin fm = new FormLogin();
		model.addAttribute("formLogin", fm);
		
		session.removeAttribute("loginUser");
		session.removeAttribute("printList");
		session.removeAttribute("pageInfo");
		session.removeAttribute("printList");
		session.removeAttribute("searchList");
		session.removeAttribute("oldFormData");
		session.removeAttribute("printNegoList");
		session.removeAttribute("customer");
		session.removeAttribute("contractTerm");
		session.removeAttribute("addFlag");

		return "Login";
	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String form(@Valid @ModelAttribute FormLogin fm, BindingResult result, Model model, HttpSession session) throws NoSuchAlgorithmException {

		System.out.println("POST");
//		String hexString = DigestUtils.md5Hex("aaaa"); 
//		System.out.println(hexString);
		
		if(result.hasErrors()) {
			model.addAttribute("loginErrorMsg", "有効なアカウント情報でログインして下さい");
			return "Login";
		}

		UserDao<User> dao = new UserDaoImpl();
		List<User> userList = dao.findByName(fm.getName());

		if(userList.size() == 0) {
			model.addAttribute("loginErrorMsg", "有効なアカウント情報でログインして下さい");
		} else if(userList.size() == 1) {
			String fmMd5pass = DigestUtils.md5Hex(fm.getPass());

			if(userList.get(0).getPass().equals(fmMd5pass)) {
				deleteUselessTodayData();
				session.setAttribute("loginUser", userList.get(0));
				return "redirect:TodayVisit";
			}
		} else {
			model.addAttribute("loginErrorMsg", "DB異常");
		}

		return "Login";
	}
	
	/*
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
	*/
}
