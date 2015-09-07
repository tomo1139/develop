package jp.com.tt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.com.tt.model.beans.Customer;
import jp.com.tt.model.beans.Negotiation;
import jp.com.tt.model.beans.PageInfo;
import jp.com.tt.model.beans.SearchPrintData;
import jp.com.tt.model.beans.Today;
import jp.com.tt.model.beans.User;
import jp.com.tt.model.dao.CustomerDao;
import jp.com.tt.model.dao.CustomerDaoImpl;
import jp.com.tt.model.dao.MyException;
import jp.com.tt.model.dao.NegotiationDao;
import jp.com.tt.model.dao.NegotiationDaoImpl;
import jp.com.tt.model.dao.TodayDao;
import jp.com.tt.model.dao.TodayDaoImpl;
import jp.com.tt.model.dao.UserDao;
import jp.com.tt.model.dao.UserDaoImpl;
import jp.com.tt.model.form.FormSearch;
import jp.com.tt.model.util.MyUtils;

@Controller
public class SearchPrint {
	
	@RequestMapping(value = "/SearchPrint", method = RequestMethod.GET)
	public String formGet(@ModelAttribute FormSearch fm, Model model, HttpSession session) {
		session.removeAttribute("searchList");
		session.removeAttribute("pageInfo");
		session.removeAttribute("oldFormData");
		return "searchPrint";
	}
	
	@RequestMapping(value = "/SearchUpdate", method = RequestMethod.POST)
	public String formPost2(@ModelAttribute FormSearch fm, Model model, HttpServletRequest request, HttpSession session) throws MyException {
		User user = (User) session.getAttribute("loginUser");
		String id = request.getParameter("customerId");
		String isToday = request.getParameter("isToday");
		
	    Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dateStr = sdf.format(date);

		TodayDao<Today> tdao = new TodayDaoImpl();
		List<Today> todayList = tdao.getCustomeridAndDay(Integer.parseInt(id), dateStr.substring(0, 10));
		
		if(todayList.size() == 0) { // 未登録
			Today tdata = new Today();
			tdata.setCustomer_id(Integer.parseInt(id));

			tdata.setDate(dateStr);
			tdata.setM_flg(0);
			tdata.setUser_id(user.getId());
			
			tdao.add(tdata);
		} else { // 登録済み

			List<Today> tlist = tdao.getAll();
			for(Today today : tlist) {
				if(MyUtils.isSameDay(today.getDate())) {
					if(Integer.parseInt(id) == today.getCustomer_id()) {
						if(today.getUser_id() == user.getId()) {
							tdao.delete(today.getId());
						}
					}
				}
			}
		}

		PageInfo pi = (PageInfo)session.getAttribute("pageInfo");

		FormSearch oldfm = (FormSearch)session.getAttribute("oldFormData");
		CustomerDao<Customer> dao = new CustomerDaoImpl();
		Integer retNum[] = new Integer[1];
		List<Customer> clist = dao.getSearchPartial(oldfm.getId(),
												    oldfm.getName(),
												    oldfm.getPeriod(),
												    oldfm.getAddress(),
												    oldfm.getPostal(),
												    (pi.getNowPage()-1)*10, 10,
												    retNum);
		
		List<SearchPrintData> slist = setSlist(clist, session);

		model.addAttribute("searchList", slist);

		List<SearchPrintData> plist = slist.subList(0, slist.size());
		session.setAttribute("printList", plist);
		
		return "searchPrint";
	}

	@RequestMapping(value = "/SearchTarget", method = RequestMethod.GET)
	public String formGetTarget(@ModelAttribute FormSearch fm, Model model, HttpServletRequest request, HttpSession session) {

		PageInfo pi = (PageInfo)session.getAttribute("pageInfo");
		int page = Integer.parseInt(request.getParameter("page"));
		if((page < 1) || (pi.getMaxPage() < page)) {
			return "searchPrint";
		}

		FormSearch oldfm = (FormSearch)session.getAttribute("oldFormData");
		CustomerDao<Customer> dao = new CustomerDaoImpl();
		Integer retNum[] = new Integer[1];
		List<Customer> clist = dao.getSearchPartial(oldfm.getId(),
												    oldfm.getName(),
												    oldfm.getPeriod(),
												    oldfm.getAddress(),
												    oldfm.getPostal(),
												    (page-1)*10, 10,
												    retNum);

		List<SearchPrintData> slist = setSlist(clist, session);
		session.setAttribute("searchList", slist);
		session.setAttribute("oldFormData", oldfm);
		
		List<SearchPrintData> plist = slist.subList(0, slist.size());
		session.setAttribute("printList", plist);
		
		PageInfo newPi = MyUtils.createPiData(page, retNum[0]);

		session.setAttribute("pageInfo", newPi);		

		return "searchPrint";
	}
	

	@RequestMapping(value = "/SearchPrint", method = RequestMethod.POST)
	public String formPost(@Valid @ModelAttribute FormSearch fm, BindingResult result, Model model, HttpServletRequest request, HttpSession session) {
		
		if(result.hasErrors()) {
			return "searchPrint";
		}

		String id = fm.getId();
		String name = fm.getName();
		String period = fm.getPeriod();
		String address = fm.getAddress();
		String postal = fm.getPostal();
		
		if(id.equals("") && name.equals("") && period.equals("") && address.equals("") && postal.equals("")) {
			session.removeAttribute("searchList");
			session.removeAttribute("pageInfo");
			session.removeAttribute("oldFormData");
			return "searchPrint";
		}

		CustomerDao<Customer> dao = new CustomerDaoImpl();
		Integer retNum[] = new Integer[1];
		List<Customer> clist = dao.getSearchPartial(id, name, period, address, postal, 0, 10, retNum);
		
		List<SearchPrintData> slist = setSlist(clist, session);
		session.setAttribute("searchList", slist);
		session.setAttribute("oldFormData", fm);
		
		List<SearchPrintData> plist = slist.subList(0, slist.size());
		session.setAttribute("printList", plist);
		
		PageInfo pi = MyUtils.createPiData(1, retNum[0]);

		session.setAttribute("pageInfo", pi);
		
		return "searchPrint";
	}
	
	private List<SearchPrintData> setSlist(List<Customer> clist, HttpSession session) {
		List<SearchPrintData> slist = new ArrayList<SearchPrintData>();
		
		User user = (User) session.getAttribute("loginUser");

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdf.format(date);

		TodayDao<Today> tdao = new TodayDaoImpl();
		List<Today> tlist = tdao.getUseridAndDay(user.getId(), dateString.substring(0, 10));
		
		for(Customer cust : clist) {
			SearchPrintData sdata = new SearchPrintData();

			sdata.setId(cust.getId());
			sdata.setName(cust.getName());
			sdata.setAddress(cust.getAddress());
			
			sdata.setIsToday(0);
			for(Today today : tlist) {
				if(cust.getId() == today.getCustomer_id()) {
					sdata.setIsToday(1);
					break;
				}
			}

			NegotiationDao<Negotiation> dao = new NegotiationDaoImpl();
			List<Negotiation> list = dao.findByCustomerId(cust.getId());
			UserDao<User> udao = new UserDaoImpl();
			
			for(Negotiation nego : list) {
				if(sdata.getNegotiateDay() == null) {
					String daytimeStr = nego.getDate();
					daytimeStr = daytimeStr.substring(0, 10);
					daytimeStr = daytimeStr.replaceAll("-", "/");
					sdata.setNegotiateDay(daytimeStr);
					sdata.setNegotiateName(udao.findById(nego.getUser_id()).getName());
				} else {
					String oldDay = sdata.getNegotiateDay();
					String oldDayNum = oldDay.replaceAll("/", "");

					String daytimeStr = nego.getDate();
					daytimeStr = daytimeStr.substring(0, 10);
					daytimeStr = daytimeStr.replaceAll("-", "/");
					String daytimeStrNum = daytimeStr.replaceAll("/", "");
					
					if(Long.parseLong(oldDayNum) < Long.parseLong(daytimeStrNum)) {
						sdata.setNegotiateDay(daytimeStr);
						sdata.setNegotiateName(udao.findById(nego.getUser_id()).getName());
					}
				}
			}

			slist.add(sdata);
		}
		
		return slist;
	}
}