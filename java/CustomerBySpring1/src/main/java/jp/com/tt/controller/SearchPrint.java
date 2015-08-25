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

import jp.com.tt.model.Customer;
import jp.com.tt.model.CustomerDao;
import jp.com.tt.model.CustomerDaoImpl;
import jp.com.tt.model.FormLogin;
import jp.com.tt.model.FormSearch;
import jp.com.tt.model.MyUtils;
import jp.com.tt.model.SearchPrintData;
import jp.com.tt.model.Today;
import jp.com.tt.model.TodayDao;
import jp.com.tt.model.TodayDaoImpl;
import jp.com.tt.model.User;

@Controller
public class SearchPrint {
	
	@RequestMapping(value = "/SearchPrint", method = RequestMethod.GET)
	public String formGet(@ModelAttribute FormSearch fm, Model model) {
		return "searchPrint";
	}
	
	@RequestMapping(value = "/SearchUpdate", method = RequestMethod.POST)
	public String formPost2(@ModelAttribute FormSearch fm, Model model, HttpServletRequest request, HttpSession session) {
		String id = request.getParameter("customerId");
		String isToday = request.getParameter("isToday");
		User user = (User) session.getAttribute("loginUser");
		
		TodayDao<Today> tdao = new TodayDaoImpl();
		
		if(isToday.equals("0")) { // 未登録
			Today tdata = new Today();
			tdata.setCustomer_id(Integer.parseInt(id));

		    Date date = new Date();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String dateStr = sdf.format(date);
			tdata.setDate(dateStr);
			tdata.setM_flg(0);
			tdata.setUser_id(user.getId());
			
			tdao.add(tdata);
		} else { // 登録済み

			List<Today> tlist = tdao.getAll();
			for(Today today : tlist) {
				if(MyUtils.isSameDay(today.getDate())) {
					if(Integer.parseInt(id) == today.getCustomer_id()) {
						tdao.delete(today.getId());
					}
				}
			}
		}


		FormSearch oldfm = (FormSearch)session.getAttribute("oldFormData");
		CustomerDao<Customer> dao = new CustomerDaoImpl();
		List<Customer> clist = dao.getSearchAll(oldfm.getId(),
												oldfm.getName(),
												oldfm.getPeriod(),
												oldfm.getAddress(),
												oldfm.getPostal());
		
		List<SearchPrintData> slist = setSlist(clist);

		model.addAttribute("searchList", slist);

		return "searchPrint";
	}

	@RequestMapping(value = "/SearchPrint", method = RequestMethod.POST)
	public String formPost(@ModelAttribute FormSearch fm, Model model, HttpServletRequest request, HttpSession session) {

		String id = fm.getId();
		String name = fm.getName();
		String period = fm.getPeriod();
		String address = fm.getAddress();
		String postal = fm.getPostal();

		CustomerDao<Customer> dao = new CustomerDaoImpl();
		List<Customer> clist = dao.getSearchAll(id, name, period, address, postal);
		
		List<SearchPrintData> slist = setSlist(clist);
		model.addAttribute("searchList", slist);
		session.setAttribute("searchList", slist);
		session.setAttribute("oldFormData", fm);
		
		return "searchPrint";
	}
	
	private List<SearchPrintData> setSlist(List<Customer> clist) {
		List<SearchPrintData> slist = new ArrayList<SearchPrintData>();
		
		TodayDao<Today> tdao = new TodayDaoImpl();
		List<Today> tlist = tdao.getAll();
		
		for(Customer cust : clist) {
			SearchPrintData sdata = new SearchPrintData();

			sdata.setId(cust.getId());
			sdata.setName(cust.getName());
			sdata.setAddress(cust.getAddress());
			
			sdata.setIsToday(0);
			for(Today today : tlist) {
				if(MyUtils.isSameDay(today.getDate())) {
					if(cust.getId() == today.getCustomer_id()) {
						sdata.setIsToday(1);
						break;
					}
				}
			}
			slist.add(sdata);
		}
		
		return slist;
	}
}