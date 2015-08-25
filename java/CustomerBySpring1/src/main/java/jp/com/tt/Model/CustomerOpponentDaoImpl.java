package jp.com.tt.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CustomerOpponentDaoImpl implements CustomerOpponentDao<CustomerOpponent> {
	private static EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("persistenceUnit");

	public List<CustomerOpponent> getAll() {
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("from CustomerOpponent");
		List<CustomerOpponent> list = query.getResultList();
		manager.close();
		return list;
	}
	
	public CustomerOpponent findById(int id) {
		EntityManager manager = factory.createEntityManager();
		CustomerOpponent data = (CustomerOpponent)manager.createQuery("from CustomerOpponent where id = " + id).getSingleResult();
		return data;
	}
}
