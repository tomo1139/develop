package jp.com.tt.model.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jp.com.tt.model.beans.Customer;

public class CustomerDaoImpl implements CustomerDao<Customer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory("persistenceUnit");
	
	public Integer getNewestDataId() {
		EntityManager manager = factory.createEntityManager();
		Integer id = (Integer)manager.createQuery("select MAX(id) from Customer").getSingleResult();
		return id;
	}

	public void add(Customer data) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(data);
		transaction.commit();
		manager.close();
	}
	
	public List<Customer> getAll() {
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("from Customer");
		List<Customer> list = (List<Customer>)query.getResultList();
		manager.close();
		return list;
	}

	public void update(Customer data) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(data);
		transaction.commit();
		manager.close();
	}

	public Customer findById(int id) throws MyException {
		EntityManager manager = factory.createEntityManager();
		Customer customer = null;

		try {

			customer = (Customer)manager.createQuery("from Customer where id = " + id).getSingleResult();
			
		} catch(Exception e) {
			throw new MyException();
		}

		return customer;
	}

	public List<Customer> getSearchPartial(String id, String name, String period, String address, String postal, int offset, int max, Integer retNum[]) {

		EntityManager manager = factory.createEntityManager();
		StringBuilder sb = new StringBuilder();
		sb.append("from Customer c where ");

		StringBuilder sb2 = new StringBuilder();
		sb2.append("select count(c) ");
		
		name = name.replaceAll("%", "\\\\%");
		address = address.replace("%", "\\\\%");
		postal = postal.replace("%", "\\\\%");

		if(!id.equals("")) { sb.append("id LIKE '" + id + "%' AND "); }
		if(!name.equals("")   ) { sb.append("name LIKE '" + name + "%' AND "); }

		if(!period.equals("") ) {
	        Date date = new Date();
	        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
	        String now = sdf1.format(date);
	        String nowYearStr = now.substring(0, 4);
	        String nowMonthStr = now.substring(5, 7);
	        
	        Integer nowYear = Integer.parseInt(nowYearStr);
	        Integer nowMonth = Integer.parseInt(nowMonthStr);
	        nowMonth -= Integer.parseInt(period);
	        while(nowMonth < 1) {
	        	nowMonth += 12;
	        	nowYear--;
	        }
	        
			sb.append("contract_date LIKE '" + nowYear.toString() + "-" + String.format("%02d", nowMonth).toString() + "%' AND ");
		}

		if(!address.equals("")) { sb.append("address LIKE '" + address + "%' AND "); }
		if(!postal.equals("") ) { sb.append("postal  LIKE '" + postal + "%' AND "); }
		
		String sql = sb.toString();
		
		if(sql.endsWith(" AND ")){
			String newSql = sql.substring(0, sql.length()-5);
			Query query = manager.createQuery(newSql).setFirstResult(offset).setMaxResults(max);
			List<Customer> list = (List<Customer>)query.getResultList();

			sb2.append(newSql);
			Query query2 = manager.createQuery(sb2.toString());
			retNum[0] = (Integer.parseInt(query2.getSingleResult().toString()));
			
			return list;
		}
		return null;
	}

	public List<Customer> getSearchAll(String id, String name, String period, String address, String postal){
		Integer retNum[] = new Integer[1];
		return getSearchPartial(id, name, period, address, postal, 0, 100000, retNum);
	}
}
