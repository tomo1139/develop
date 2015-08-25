package jp.com.tt.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserDaoImpl implements UserDao<User> {
	private static EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("persistenceUnit");

	public List<User> getAll() {
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("from User");
		List<User> list = query.getResultList();
		manager.close();
		return list;
	}

	public void add(User data) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(data);
		transaction.commit();
		manager.close();
	}

	public User findById(int id) {
		EntityManager manager = factory.createEntityManager();
		User data = (User)manager.createQuery("from User where id = " + id).getSingleResult();
		return data;
	}

	public void update(User data) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(data);
		transaction.commit();
		manager.close();
	}

	public void delete(User data) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		User entity = manager.merge(data);
		manager.remove(entity);
		transaction.commit();
		manager.close();
	}
	
	public void delete(int id) {
		delete(findById(id));
	}

}
