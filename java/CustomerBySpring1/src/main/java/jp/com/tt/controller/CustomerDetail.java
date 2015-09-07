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
import jp.com.tt.model.beans.Negotiation;
import jp.com.tt.model.beans.NegotiationPrint;
import jp.com.tt.model.beans.PageInfo;
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
import jp.com.tt.model.dao.NegotiationDao;
import jp.com.tt.model.dao.NegotiationDaoImpl;
import jp.com.tt.model.dao.TodayDao;
import jp.com.tt.model.dao.TodayDaoImpl;
import jp.com.tt.model.form.FormAddCustomer;
import jp.com.tt.model.form.FormEdit;
import jp.com.tt.model.form.FormRegist;
import jp.com.tt.model.util.MyUtils;

@Controller
public class CustomerDetail {

	@RequestMapping(value = "/detailTarget", method = RequestMethod.GET)
	public String formGetTarget(Model model, HttpServletRequest request, HttpSession session) {

		FormAddCustomer fmac = new FormAddCustomer();
		model.addAttribute("formAddCustomer", fmac);

		PageInfo pi = (PageInfo)session.getAttribute("pageInfo");
		int page = Integer.parseInt(request.getParameter("page"));
		if((page < 1) || (pi.getMaxPage() < page)) {
			return "customerDetail";
		}

		List<NegotiationPrint> nplistAll = (List<NegotiationPrint>)(session.getAttribute("negoList"));
		PageInfo newPi = MyUtils.createPiData(page, nplistAll.size());
		session.setAttribute("pageInfo", newPi);

		List<NegotiationPrint> nplistSub = nplistAll.subList(newPi.getPrintStartDataIdx(), newPi.getPrintEndDataIdx()+1);
		session.setAttribute("printNegoList", nplistSub);
		
		return "customerDetail";
	}
		
	@RequestMapping(value = "/CustomerDetail", method = RequestMethod.GET)
	public String form(Model model, HttpServletRequest request, HttpSession session) throws ParseException {
		
		FormAddCustomer fm = new FormAddCustomer();
		model.addAttribute("formAddCustomer", fm);

		String id = request.getParameter("id");
		if(id != null) {
			try {
				MyUtils.addPrintModel(model, Integer.parseInt(id), session);
			} catch (MyException e) {
				return "error";
			}
		}

		return "customerDetail";
	}

	/*
	@RequestMapping(value = "/CustomerDetailDel", method = RequestMethod.POST)
	public String formDelete(Model model, HttpServletRequest request, HttpSession session) throws ParseException {

		FormAddCustomer fmac = new FormAddCustomer();
		model.addAttribute("formAddCustomer", fmac);

		String negoId = request.getParameter("negoId");

		if(negoId != null) {
			try {
				NegotiationDao<Negotiation> ndao = new NegotiationDaoImpl();
				Negotiation nego = (Negotiation) ndao.findById(Integer.parseInt(negoId));
				nego.setFlg_del(1);
				ndao.update(nego);
				
			} catch (MyException e) {
				;
			}
		}
		try {
			MyUtils.addPrintModel(model, request, session);
		} catch (MyException e) {
			return "error";
		}
		return "customerDetail";
	}
	*/
	
	@RequestMapping(value = "/CustomerDetail", method = RequestMethod.POST)
	public String form(@ModelAttribute FormRegist fm, Model model, HttpServletRequest request, HttpSession session) throws ParseException, NumberFormatException, MyException {

		FormAddCustomer fmac = new FormAddCustomer();
		model.addAttribute("formAddCustomer", fmac);

		String id = request.getParameter("id");

		String customerId = request.getParameter("id");
		String datetime = request.getParameter("datetime");
		String means = request.getParameter("means");
		String opponent = request.getParameter("opponent");
		String status = request.getParameter("status");
		String detail = request.getParameter("detail");
		String todayId = request.getParameter("todayId");

		//String count = request.getParameter("count");

		Negotiation nego = new Negotiation();

		nego.setCustomer_id(Integer.parseInt(customerId));
		User user = (User) session.getAttribute("loginUser");
		nego.setUser_id(user.getId());
		nego.setMeans(Integer.parseInt(means));
		nego.setOpponent(Integer.parseInt(opponent));
		nego.setStatus(Integer.parseInt(status));
        datetime = datetime + ":00";
        datetime = datetime.replaceAll("/", "-");
		nego.setDate(datetime);
		nego.setDetail(detail);

		if(detail.equals("")) {
		}
		else {
			NegotiationDao<Negotiation> dao = new NegotiationDaoImpl();
			dao.add(nego);
		}

		if(detail.equals("")) {
			{
				CustomerDao<Customer> dao = new CustomerDaoImpl();
				Customer cust = null;
				try {
					cust = dao.findById(Integer.parseInt(id));
				} catch (NumberFormatException e) {
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
        	model.addAttribute("method", means);
        	model.addAttribute("opponent", opponent);
        	model.addAttribute("result", status);
        
        	fm.setDetail(detail);
        	model.addAttribute("detailEdit", detail);
        
        	model.addAttribute("loginErrorMsg", "detailは必須項目です");
			return "registNegotiate";
		} else {

			try {
				MyUtils.addPrintModel(model, Integer.parseInt(id), session);
			} catch (MyException e) {
				return "error";
			}
		
			TodayDao<Today> tdao = new TodayDaoImpl();
			Today tdata = tdao.findById(Integer.parseInt(todayId));
			tdata.setM_flg(1);
			tdao.update(tdata);

			return "redirect:CustomerDetail";
		}
	}

	@RequestMapping(value = "/CustomerDetailEdit", method = RequestMethod.POST)
	public String form(@ModelAttribute FormEdit fm, Model model, HttpServletRequest request, HttpSession session) throws ParseException {

		FormAddCustomer fmac = new FormAddCustomer();
		model.addAttribute("formAddCustomer", fmac);

		String id = request.getParameter("id");
		String customerId = request.getParameter("id");
		String datetime = request.getParameter("datetime");
		String means = request.getParameter("means");
		String opponent = request.getParameter("opponent");
		String status = request.getParameter("status");
		String detail = request.getParameter("detailEdit");
		String negoId = request.getParameter("negoId");
		
		//String count = request.getParameter("count");

		Negotiation nego;

		try {
			NegotiationDao<Negotiation> dao = new NegotiationDaoImpl();
			nego = dao.findById(Integer.parseInt(negoId));
			nego.setCustomer_id(Integer.parseInt(customerId));
			User user = (User) session.getAttribute("loginUser");
			nego.setUser_id(user.getId());
			nego.setMeans(Integer.parseInt(means));
			nego.setOpponent(Integer.parseInt(opponent));
			nego.setStatus(Integer.parseInt(status));
	       	datetime = datetime + ":00";
	       	datetime = datetime.replaceAll("/", "-");
			nego.setDate(datetime);
			nego.setDetail(detail);
		
			if(detail.equals("")) {
			}
			else {
				dao.update(nego);
			}

		} catch (MyException e) {
			;
		}

		if(detail.equals("")) {
			{
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
		
        	model.addAttribute("datetime", datetime);
        	model.addAttribute("method", means);
        	model.addAttribute("opponent", opponent);
        	model.addAttribute("result", status);
        	model.addAttribute("negoId", negoId);
        
        	fm.setDetailEdit(detail);
        	model.addAttribute("detailEdit", detail);
        
        	model.addAttribute("loginErrorMsg", "detailは必須項目です");
			return "registNegotiateEdit";
		} else {
			try {
				MyUtils.addPrintModel(model, Integer.parseInt(id), session);
			} catch (MyException e) {
				return "error";
			}
			return "redirect:CustomerDetail";
		}
	}
}
