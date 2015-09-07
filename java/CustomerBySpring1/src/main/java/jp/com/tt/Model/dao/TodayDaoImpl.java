package jp.com.tt.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jp.com.tt.model.beans.Today;

public class TodayDaoImpl implements TodayDao<Today> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("persistenceUnit");
	
	public List<Today> getUserid(Integer id) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		String sql = "from Today where user_id = :idStr";
		Query query = manager.createQuery(sql).setParameter("idStr", id);
		List<Today> list = (List<Today>)(query.getResultList());
		transaction.commit();
		manager.close();
		return list;
	}
	
	public List<Today> getUseridAndNotDay(Integer id, String date) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		String sql = "from Today where user_id = :idStr AND date NOT LIKE :dateStr";
		Query query = manager.createQuery(sql).setParameter("idStr", id).setParameter("dateStr", date+"%");
		List<Today> list = (List<Today>)(query.getResultList());
		transaction.commit();
		manager.close();
		return list;
	}
	
	public List<Today> getCustomeridAndDay(Integer id, String date) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		String sql = "from Today where customer_id = :idStr AND date LIKE :dateStr";
		Query query = manager.createQuery(sql).setParameter("idStr", id).setParameter("dateStr", date+"%");
		List<Today> list = (List<Today>)(query.getResultList());
		transaction.commit();
		manager.close();
		return list;
	}

	public List<Today> getUseridAndDay(Integer id, String date) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		String sql = "from Today where user_id = :idStr AND date LIKE :dateStr";
		Query query = manager.createQuery(sql).setParameter("idStr", id).setParameter("dateStr", date+"%");
		List<Today> list = (List<Today>)(query.getResultList());
		transaction.commit();
		manager.close();
		return list;
	}

	public List<Today> getNotSameDay(String date) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		String sql = "from Today where date NOT LIKE :dateStr";
		Query query = manager.createQuery(sql).setParameter("dateStr", date+"%");
		List<Today> list = (List<Today>)(query.getResultList());
		transaction.commit();
		manager.close();
		return list;
	}

	public List<Today> getAll() {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Query query = manager.createQuery("from Today");
		List<Today> list = query.getResultList();
		transaction.commit();
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

	public Today findById(int id) throws MyException {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Today today = null;
		try {
			today = (Today)manager.createQuery("from Today where id = " + id).getSingleResult();
		} catch(Exception e) {
			transaction.commit();
			manager.close();
			throw new MyException();
		}
		manager.close();
		transaction.commit();

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
	
	public void delete(int id) throws MyException {
		delete(findById(id));
	}

}
