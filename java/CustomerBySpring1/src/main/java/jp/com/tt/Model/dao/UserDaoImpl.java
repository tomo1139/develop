package jp.com.tt.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jp.com.tt.model.beans.User;

public class UserDaoImpl implements UserDao<User> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	public List<User> findByName(String name) {
		EntityManager manager = factory.createEntityManager();
		String sql = "from User where name = :nameStr";
		Query query = manager.createQuery(sql).setParameter("nameStr", name);
		List<User> list = (List<User>)(query.getResultList());
		manager.close();
		return list;
	}

	public User findById(int id) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		User data = (User)manager.createQuery("from User where id = " + id).getSingleResult();
		transaction.commit();
		manager.close();
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
