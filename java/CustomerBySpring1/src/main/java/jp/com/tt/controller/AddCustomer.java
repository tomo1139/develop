package jp.com.tt.controller;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.com.tt.model.beans.Customer;
import jp.com.tt.model.dao.CustomerDao;
import jp.com.tt.model.dao.CustomerDaoImpl;
import jp.com.tt.model.dao.MyException;
import jp.com.tt.model.form.FormAddCustomer;
import jp.com.tt.model.util.MyUtils;

@Controller
public class AddCustomer {
	
	@RequestMapping(value = "/AddCustomer", method = RequestMethod.GET)
	public String formGet(Model model, HttpSession session) {

		FormAddCustomer fm = new FormAddCustomer();
		model.addAttribute("formAddCustomer", fm);

		MyUtils.addSelect(model);

		return "addCustomer";
	}
	
	public boolean isFormError(FormAddCustomer fm) {
		if(fm.getName().equals("") ||
		   fm.getAddress().equals("") ||
		   fm.getPostal().equals("") ||
		   fm.getHome_phone().equals("") ||
		   fm.getMobile_phone().equals("") ||
		   fm.getEmail().equals("") ||
		   fm.getManagement_type().equals("") ||
		   fm.getContract_date().equals("") ||
		   fm.getPayment_method().equals("") ||
		   fm.getPayment_course().equals("") ) {
			return true;
		}
		
		{
			String r = "^\\d{3}-\\d{4}$";
			Pattern p = Pattern.compile(r);
			Matcher m = p.matcher(fm.getPostal());
			if(!m.find()) { return true; }
		}

		{
			String r = "^\\d{10}$";
			Pattern p = Pattern.compile(r);
			Matcher m = p.matcher(fm.getHome_phone());
			if(!m.find()) { return true; }
		}

		{
			String r = "^\\d{11}$";
			Pattern p = Pattern.compile(r);
			Matcher m = p.matcher(fm.getMobile_phone());
			if(!m.find()) { return true; }
		}

		{
			String r = "^[a-zA-Z0-9]+@[a-zA-Z0-9.]+$";
			Pattern p = Pattern.compile(r);
			Matcher m = p.matcher(fm.getEmail());
			if(!m.find()) { return true; }
		}

		return false;
	}

	@RequestMapping(value = "/EditCustomer", method = RequestMethod.POST)
	public String formPostEdit(@ModelAttribute FormAddCustomer fm, Model model, HttpServletRequest request, HttpSession session) throws NumberFormatException, MyException, ParseException {
		
		if(isFormError(fm)) {
			model.addAttribute("addFailMsg", "更新に失敗しました");
		}
		else {
			CustomerDao<Customer> dao = new CustomerDaoImpl();

			Customer cust = dao.findById(Integer.parseInt(fm.getId()));

			cust.setName(fm.getName());
			cust.setAddress(fm.getAddress());
			cust.setPostal(fm.getPostal());
			cust.setHome_phone(fm.getHome_phone());
			cust.setMobile_phone(fm.getMobile_phone());
			cust.setEmail(fm.getEmail());
			cust.setManagement_type(fm.getManagement_type());
			cust.setContract_date(fm.getContract_date().replaceAll("/", "-"));
			cust.setPayment_method(fm.getPayment_method());
			cust.setPayment_course(fm.getPayment_course());

			dao.update(cust);

			model.addAttribute("id", fm.getId());
			
			String id = fm.getId();

			if(id != null) {
				try {
					MyUtils.addPrintModel(model, Integer.parseInt(id), session);
				} catch (MyException e) {
					return "error";
				}
			}

			return "redirect:CustomerDetail";
		}

		MyUtils.addSelect(model);

		return "editCustomer";
	}

	@RequestMapping(value = "/UpdateCustomer", method = RequestMethod.POST)
	public String formPostUpdate(@ModelAttribute FormAddCustomer fm, Model model, HttpSession session) {

		MyUtils.addSelect(model);
		
		Customer cust = (Customer)session.getAttribute("customer");
		System.out.println(cust.getContract_date());
		model.addAttribute("contract_date_print", cust.getContract_date().replaceAll("-", "/"));

		return "editCustomer";
	}

	@RequestMapping(value = "/AddCustomer", method = RequestMethod.POST)
	public String formPost(@ModelAttribute FormAddCustomer fm, Model model, HttpSession session, HttpServletRequest request) throws ParseException {
		
		if(isFormError(fm)) {
			model.addAttribute("addFailMsg", "更新に失敗しました");
			MyUtils.addSelect(model);
		} else {
			Customer cust = new Customer();
			cust.setName(fm.getName());
			cust.setAddress(fm.getAddress());
			cust.setPostal(fm.getPostal());
			cust.setHome_phone(fm.getHome_phone());
			cust.setMobile_phone(fm.getMobile_phone());
			cust.setEmail(fm.getEmail());
			cust.setManagement_type(fm.getManagement_type());
			cust.setContract_date(fm.getContract_date().replaceAll("/", "-"));
			cust.setPayment_method(fm.getPayment_method());
			cust.setPayment_course(fm.getPayment_course());

			CustomerDao<Customer> dao = new CustomerDaoImpl();

			dao.add(cust);
			
			Integer newestId = dao.getNewestDataId();

			request.setAttribute("id", newestId);
			model.addAttribute("id", newestId);
			
			String id = newestId.toString();

			if(id != null) {
				try {
					MyUtils.addPrintModel(model, Integer.parseInt(id), session);
				} catch (MyException e) {
					return "error";
				}
			}

			return "redirect:CustomerDetail";
		}

		return "addCustomer";
	}
}
