package jp.com.tt.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TodayDaoImpl implements TodayDao<Today> {
	private static EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("persistenceUnit");

	public List<Today> getAll() {
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("from Today");
		List<Today> list = query.getResultList();
		manager.close();
		return list;
	}

	public void add(Today data) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(data);
		transaction.commit();
		manager.close();
	}

	public Today findById(int id) {
		EntityManager manager = factory.createEntityManager();
		Today today = (Today)manager.createQuery("from Today where id = " + id).getSingleResult();
		return today;
	}

	public void update(Today data) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(data);
		transaction.commit();
		manager.close();
	}

	public void delete(Today data) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Today entity = manager.merge(data);
		manager.remove(entity);
		transaction.commit();
		manager.close();
	}
	
	public void delete(int id) {
		delete(findById(id));
	}

}
