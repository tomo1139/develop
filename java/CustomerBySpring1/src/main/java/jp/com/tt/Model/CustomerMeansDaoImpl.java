package jp.com.tt.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CustomerMeansDaoImpl implements CustomerMeansDao<CustomerMeans> {
	private static EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("persistenceUnit");

	public List<CustomerMeans> getAll() {
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("from CustomerMeans");
		List<CustomerMeans> list = query.getResultList();
		manager.close();
		return list;
	}
	
	public CustomerMeans findById(int id) {
		EntityManager manager = factory.createEntityManager();
		CustomerMeans data = (CustomerMeans)manager.createQuery("from CustomerMeans where id = " + id).getSingleResult();
		return data;
	}
}
