package jp.com.tt.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.ui.Model;

public class MyUtils {
	public static List<TodayPrintData> getPrintTodayList(User user) {
		TodayDao<Today> todayDao = new TodayDaoImpl();
		List<Today> todayList = todayDao.getAll();

		List<TodayPrintData> printTodayList = new ArrayList<TodayPrintData>();

		CustomerDao<Customer> customerDao = new CustomerDaoImpl();
		List<Customer> customerList = customerDao.getAll();

		for(Today today : todayList) {
			if(today.getUser_id() == user.getId()) {

				if(isSameDay(today.getDate())) {
					setTodayData(printTodayList, today, customerList);
				} else {
					;
				}
			}
		}
		
		return printTodayList;
	}

	public static List<TodayPrintData> getPrintTodayUnregList(User user) {
		TodayDao<Today> todayDao = new TodayDaoImpl();
		List<Today> todayList = todayDao.getAll();

		List<TodayPrintData> printTodayUnregList = new ArrayList<TodayPrintData>();

		CustomerDao<Customer> customerDao = new CustomerDaoImpl();
		List<Customer> customerList = customerDao.getAll();

		for(Today today : todayList) {
			if(today.getUser_id() == user.getId()) {
				if(isSameDay(today.getDate())) {
					;
				} else {
					if(today.getM_flg() == 0) {
						setTodayData(printTodayUnregList, today, customerList);
					}
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

		if(dateString.substring(0, 11).equals(_date.substring(0, 11))) {
			return true;
		}
		return false;
	}
	
	private static void setTodayData(List list, Today today, List<Customer> customerList) {
		TodayPrintData tmp = new TodayPrintData();

		tmp.setId(today.getCustomer_id());
		tmp.setTodayPk(today.getId());
		for(Customer cust : customerList) {
			if(cust.getId() == today.getCustomer_id()) {
				tmp.setName(cust.getName());
				tmp.setAddress(cust.getAddress());
				tmp.setM_flg(today.getM_flg());
				tmp.setContractDate(cust.getContract_date());
				break;
			}
		}
		list.add(tmp);
	}
}
