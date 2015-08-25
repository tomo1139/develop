package jp.com.tt.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CustomerDaoImpl implements CustomerDao<Customer> {
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory("persistenceUnit");
	
	public List<Customer> getAll() {
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("from Customer");
		List<Customer> list = query.getResultList();
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

	public Customer findById(int id) {
		EntityManager manager = factory.createEntityManager();
		Customer customer = (Customer)manager.createQuery("from Customer where id = " + id).getSingleResult();
		return customer;
	}

	public List<Customer> getSearchAll(String id, String name, String period, String address, String postal){
		EntityManager manager = factory.createEntityManager();
		StringBuilder sb = new StringBuilder();
		sb.append("from Customer where ");
		if(!id.equals("")) { sb.append("id LIKE '" + id + "%' AND "); }
		if(!name.equals("")   ) { sb.append("name LIKE '" + name + "%' AND "); }
		if(!period.equals("") ) { sb.append("period LIKE '" + period + "%' AND "); }
		if(!address.equals("")) { sb.append("address LIKE '" + address + "%' AND "); }
		if(!postal.equals("") ) { sb.append("postal  LIKE '" + postal + "%' AND "); }
		
		String sql = sb.toString();
		

		if(sql.endsWith(" AND ")){
			String newSql = sql.substring(0, sql.length()-5);
			System.out.println(newSql);
			List<Customer> list = (List<Customer>)manager.createQuery(newSql).getResultList();
			System.out.println("list size: " + list.size());
			return list;
		}
		return null;
	}
}
