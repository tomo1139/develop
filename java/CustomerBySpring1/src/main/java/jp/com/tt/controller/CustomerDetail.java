package jp.com.tt.controller;

import java.text.ParseException;
import java.util.ArrayList;
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
import jp.com.tt.model.FormRegist;
import jp.com.tt.model.Negotiation;
import jp.com.tt.model.NegotiationDao;
import jp.com.tt.model.NegotiationDaoImpl;
import jp.com.tt.model.NegotiationPrint;
import jp.com.tt.model.Today;
import jp.com.tt.model.TodayDao;
import jp.com.tt.model.TodayDaoImpl;
import jp.com.tt.model.User;

@Controller
public class CustomerDetail {
	private void addPrintModel(Model model, HttpServletRequest request, HttpSession session) {
		String id = request.getParameter("id");
		CustomerDao<Customer> dao = new CustomerDaoImpl();
		Customer cust = dao.findById(Integer.parseInt(id));
		model.addAttribute("customer", cust);

		NegotiationDao<Negotiation> ndao = new NegotiationDaoImpl();
		List<Negotiation> nlist = ndao.getAll();
		User user = (User) session.getAttribute("loginUser");

		List<NegotiationPrint> npList = new ArrayList<NegotiationPrint>();

		CustomerMeansDao<CustomerMeans> mdao = new CustomerMeansDaoImpl();
		CustomerOpponentDao<CustomerOpponent> odao = new CustomerOpponentDaoImpl();
		CustomerStatusDao<CustomerStatus> sdao = new CustomerStatusDaoImpl();

		for(Negotiation nego : nlist) {
			if(nego.getCustomer_id() == cust.getId()) {
				if(user.getId() == nego.getUser_id()) {
					NegotiationPrint np = new NegotiationPrint();
					np.setDate(nego.getDate().substring(0, 11));
					np.setTime(nego.getDate().substring(11, 16));
					System.out.println(nego.getMeans());
					np.setMethod(mdao.findById(nego.getMeans()).getComs());
					np.setOpponent(odao.findById(nego.getOpponent()).getComs());
					np.setResult(sdao.findById(nego.getStatus()).getComs());
					np.setDetail(nego.getDetail());
					npList.add(np);
				}
			}
		}

		model.addAttribute("printNegoList", npList);
	}
	
	@RequestMapping(value = "/CustomerDetail", method = RequestMethod.GET)
	public String form(Model model, HttpServletRequest request, HttpSession session) {
		addPrintModel(model, request, session);
		return "customerDetail";
	}
	
	@RequestMapping(value = "/CustomerDetail", method = RequestMethod.POST)
	public String form(@ModelAttribute FormRegist fm, Model model, HttpServletRequest request, HttpSession session) throws ParseException {
		User user = (User) session.getAttribute("loginUser");

		String customerId = request.getParameter("id");
		String datetime = request.getParameter("datetime");
		String means = request.getParameter("means");
		String opponent = request.getParameter("opponent");
		String status = request.getParameter("status");
		String detail = request.getParameter("detail");
		String todayId = request.getParameter("todayId");

		String count = request.getParameter("count");

		if(Integer.parseInt(count) == Counter.getAndInc()) {
			NegotiationDao<Negotiation> dao = new NegotiationDaoImpl();
			Negotiation nego = new Negotiation();

			nego.setCustomer_id(Integer.parseInt(customerId));
			nego.setUser_id(user.getId());
			nego.setMeans(Integer.parseInt(means));
			nego.setOpponent(Integer.parseInt(opponent));
			nego.setStatus(Integer.parseInt(status));
        	datetime = datetime + ":00";
        	datetime = datetime.replaceAll("/", "-");
			nego.setDate(datetime);
			nego.setDetail(detail);
			dao.add(nego);
		}

		addPrintModel(model, request, session);
		
		TodayDao<Today> tdao = new TodayDaoImpl();
		Today tdata = tdao.findById(Integer.parseInt(todayId));
		tdata.setM_flg(1);
		tdao.update(tdata);

		return "customerDetail";
	}
}