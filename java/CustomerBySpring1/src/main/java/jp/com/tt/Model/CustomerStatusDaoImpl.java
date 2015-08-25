package jp.com.tt.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CustomerStatusDaoImpl implements CustomerStatusDao<CustomerStatus> {
	private static EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("persistenceUnit");

	public List<CustomerStatus> getAll() {
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("from CustomerStatus");
		List<CustomerStatus> list = query.getResultList();
		manager.close();
		return list;
	}
	
	public CustomerStatus findById(int id) {
		EntityManager manager = factory.createEntityManager();
		CustomerStatus data = (CustomerStatus)manager.createQuery("from CustomerStatus where id = " + id).getSingleResult();
		return data;
	}
}
