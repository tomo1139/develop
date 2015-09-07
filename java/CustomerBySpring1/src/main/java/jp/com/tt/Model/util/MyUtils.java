package jp.com.tt.model.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import jp.com.tt.model.beans.Customer;
import jp.com.tt.model.beans.CustomerMeans;
import jp.com.tt.model.beans.CustomerOpponent;
import jp.com.tt.model.beans.CustomerStatus;
import jp.com.tt.model.beans.DataContainer;
import jp.com.tt.model.beans.Negotiation;
import jp.com.tt.model.beans.NegotiationPrint;
import jp.com.tt.model.beans.PageInfo;
import jp.com.tt.model.beans.Today;
import jp.com.tt.model.beans.TodayPrintData;
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
import jp.com.tt.model.dao.UserDao;
import jp.com.tt.model.dao.UserDaoImpl;

public class MyUtils {

	public static int getLineNum(String str) {

	    Pattern p = Pattern.compile("\n");
	    Matcher m = p.matcher(str);

	    int count = 0;
	    int s = 0;
	    while (m.find(s)) {
	        count++;
	        s = m.end();
	    }
		return count;
	}

	public static void addPrintModel(Model model, Integer id, HttpSession session) throws ParseException, MyException {

		CustomerDao<Customer> dao = new CustomerDaoImpl();
		Customer cust = dao.findById(id);

		session.setAttribute("customer", cust);
		session.setAttribute("contractTerm", MyUtils.getContractTerm(cust.getContract_date()));

		NegotiationDao<Negotiation> ndao = new NegotiationDaoImpl();
		List<Negotiation> nlist = ndao.findByCustomerId(cust.getId());

		List<NegotiationPrint> npList = new ArrayList<NegotiationPrint>();

		CustomerMeansDao<CustomerMeans> mdao = new CustomerMeansDaoImpl();
		CustomerOpponentDao<CustomerOpponent> odao = new CustomerOpponentDaoImpl();
		CustomerStatusDao<CustomerStatus> sdao = new CustomerStatusDaoImpl();

		UserDao<User> udao = new UserDaoImpl();
		
		for(Negotiation nego : nlist) {
			if(nego.getFlg_del() == 0) {
				NegotiationPrint np = new NegotiationPrint();
				np.setUserId(nego.getUser_id());
				np.setUserName(udao.findById(nego.getUser_id()).getName());
				np.setNegoId(nego.getId());
				np.setDate(nego.getDate().substring(0, 11));
				np.setTime(nego.getDate().substring(11, 16));
				np.setMethod(mdao.findById(nego.getMeans()).getComs());
				np.setOpponent(odao.findById(nego.getOpponent()).getComs());
				np.setResult(sdao.findById(nego.getStatus()).getComs());
					
				String detailStr = nego.getDetail();
				detailStr = detailStr.replaceAll("\n", "<br>");
				np.setDetail(detailStr);

				np.setLineNum(getLineNum(nego.getDetail()));

				npList.add(np);
			}
		}
		
		Collections.sort(npList, new Comparator<NegotiationPrint>() {
			public int compare(NegotiationPrint o1, NegotiationPrint o2) {
				String dateTimeStr1 = o1.getDate() + o1.getTime();
				dateTimeStr1 = dateTimeStr1.replaceAll("-", "");
				dateTimeStr1 = dateTimeStr1.replaceAll(" ", "");
				dateTimeStr1 = dateTimeStr1.replaceAll(":", "");

				String dateTimeStr2 = o2.getDate() + o2.getTime();
				dateTimeStr2 = dateTimeStr2.replaceAll("-", "");
				dateTimeStr2 = dateTimeStr2.replaceAll(" ", "");
				dateTimeStr2 = dateTimeStr2.replaceAll(":", "");
				
				if(Long.parseLong(dateTimeStr2) == Long.parseLong(dateTimeStr1)) {
					return -1;
				}
				else {
					return (int)(Long.parseLong(dateTimeStr2) - Long.parseLong(dateTimeStr1));
				}
			}
		});
		
		session.setAttribute("negoList", npList);

		PageInfo pi = MyUtils.createPiData(1, npList.size());
		session.setAttribute("pageInfo", pi);

		List<NegotiationPrint> plist = npList.subList(0, (npList.size() > 10) ? 10 : npList.size());
		session.setAttribute("printNegoList", plist);
	}

	public static void addSelect(Model model) {
		{
			List<DataContainer> list = new ArrayList<DataContainer>();
			DataContainer d1 = new DataContainer(1, "地上契約");
			list.add(d1);
			DataContainer d2 = new DataContainer(2, "衛星契約");
			list.add(d2);
			DataContainer d3 = new DataContainer(3, "特別契約");
			list.add(d3);
			model.addAttribute("managementTypeList", list);
		}
		
		{
			List<DataContainer> list = new ArrayList<DataContainer>();
			DataContainer d1 = new DataContainer(1, "口座振替");
			list.add(d1);
			DataContainer d2 = new DataContainer(2, "継続振込");
			list.add(d2);
			DataContainer d3 = new DataContainer(3, "クレジットカード継続払い");
			list.add(d3);
			model.addAttribute("paymentMethodList", list);
		}
		
		{
			List<DataContainer> list = new ArrayList<DataContainer>();
			DataContainer d1 = new DataContainer(1, "2ヶ月前払い");
			list.add(d1);
			DataContainer d2 = new DataContainer(2, "6ヶ月前払い");
			list.add(d2);
			DataContainer d3 = new DataContainer(3, "12ヶ月前払い");
			list.add(d3);
			model.addAttribute("paymentCourseList", list);
		}
		
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        model.addAttribute("nowdatetime", sdf.format(date).substring(0, 10).replaceAll("-", "/"));
	}

	public static int differenceMonth(Date date1, Date date2) {
	    Calendar cal1 = Calendar.getInstance();
	    cal1.setTime(date1);
	    cal1.set(Calendar.DATE, 1);
	    Calendar cal2 = Calendar.getInstance(); 
	    cal2.setTime(date2);
	    cal2.set(Calendar.DATE, 1);
	    int count = 0;
	    if (cal1.before(cal2)) {
	        while (cal1.before(cal2)) {
	            cal1.add(Calendar.MONTH, 1);
	            count--;
	        }
	    } else {
	        count--;
	        while (!cal1.before(cal2)) {
	            cal1.add(Calendar.MONTH, -1);
	            count++;
	        }
	    }
	    return count;
	}

	public static int differenceMonth(String strDate1, String strDate2) throws ParseException {
		Date date1 = DateFormat.getDateInstance().parse(strDate1);
		Date date2 = DateFormat.getDateInstance().parse(strDate2);
		return differenceMonth(date1,date2);
	}

	public static int getContractTerm(String contractDate) throws ParseException {
		
		contractDate = contractDate.replaceAll("-", "/");

        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        String now = sdf1.format(date);

		return differenceMonth(now, contractDate);
	}

	public static List<TodayPrintData> getPrintTodayList(User user) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdf.format(date);

		TodayDao<Today> todayDao = new TodayDaoImpl();
		List<Today> todayList = todayDao.getUseridAndDay(user.getId(), dateString.substring(0, 10));

		CustomerDao<Customer> customerDao = new CustomerDaoImpl();
		List<TodayPrintData> printTodayList = new ArrayList<TodayPrintData>();

		for(Today today : todayList) {
			try {
				Customer cust = customerDao.findById(today.getCustomer_id());

				TodayPrintData tmp = new TodayPrintData();
				tmp.setId(today.getCustomer_id());
				tmp.setTodayPk(today.getId());
				tmp.setName(cust.getName());
				tmp.setAddress(cust.getAddress());
				tmp.setM_flg(today.getM_flg());
				tmp.setContractDate(cust.getContract_date());

				printTodayList.add(0, tmp);
			} catch (MyException e) {
				;
			}
		}
		
		return printTodayList;
	}

	public static List<TodayPrintData> getPrintTodayUnregList(User user) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdf.format(date);

		TodayDao<Today> todayDao = new TodayDaoImpl();
		List<Today> todayList = todayDao.getUseridAndNotDay(user.getId(), dateString.substring(0, 10));

		CustomerDao<Customer> customerDao = new CustomerDaoImpl();
		List<TodayPrintData> printTodayUnregList = new ArrayList<TodayPrintData>();

		for(Today today : todayList) {
			if(today.getM_flg() == 0) {
				try {
					Customer cust = customerDao.findById(today.getCustomer_id());

					TodayPrintData tmp = new TodayPrintData();
					tmp.setId(today.getCustomer_id());
					tmp.setTodayPk(today.getId());
					tmp.setName(cust.getName());
					tmp.setAddress(cust.getAddress());
					tmp.setM_flg(today.getM_flg());
					tmp.setContractDate(cust.getContract_date());

					printTodayUnregList.add(0, tmp);
				} catch (MyException e) {
					;
				}
			}
		}

		return printTodayUnregList;
	}
	
	public static Date getNowDate() {
		Date date = new Date();
		return date;
	}
	
	public static boolean isSameDay(String _date) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdf.format(date);

		if(dateString.substring(0, 10).equals(_date.substring(0, 10))) {
			return true;
		}
		return false;
	}
	
	public static PageInfo createPiData(int nowPage, int printListSize) {

		PageInfo pi = new PageInfo();

		pi.setNowPage(nowPage);

		pi.setPrintStartDataIdx((nowPage-1) * 10);
		
		int endDataIdx = (nowPage*10-1);
		if(endDataIdx >= printListSize) {
			endDataIdx = printListSize-1;
		}
		pi.setPrintEndDataIdx(endDataIdx);

		int maxPage;
		if(printListSize == 0) {
			maxPage = 1;
		}
		else {
			maxPage = ((printListSize-1) / 10) + 1;
		}
		pi.setMaxPage(maxPage);
		pi.setDataNum(printListSize);

		List<Integer> printPageList = new ArrayList<Integer>();
		int startPage = nowPage-2;
		while(startPage <= 0) {
			startPage++;
		}
		for(int i=0; i<5; i++) {
			if(maxPage >= startPage) {
				printPageList.add(startPage++);
			}
			else {
				if(printPageList.get(0) != 1) {
					printPageList.add(0, printPageList.get(0)-1);
				}
			}
		}
		pi.setPrintPageList(printPageList);

		return pi;
	}
}
