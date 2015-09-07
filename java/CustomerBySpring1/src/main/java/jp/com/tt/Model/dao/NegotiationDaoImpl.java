package jp.com.tt.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jp.com.tt.model.beans.Negotiation;
import jp.com.tt.model.beans.User;

public class NegotiationDaoImpl implements NegotiationDao<Negotiation> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("persistenceUnit");

	public List<Negotiation> findByCustomerId(Integer id) {
		EntityManager manager = factory.createEntityManager();
		String sql = "from Negotiation where customer_id = :idStr";
		Query query = manager.createQuery(sql).setParameter("idStr", id);
		return (List<Negotiation>)(query.getResultList());
	}

	public List<Negotiation> getAll() {
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("from Negotiation");
		List<Negotiation> list = query.getResultList();
		manager.close();
		return list;
	}

	public void add(Negotiation data) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(data);
		transaction.commit();
		manager.close();
	}

	public Negotiation findById(int id) throws MyException {
		EntityManager manager = factory.createEntityManager();
		Negotiation negotiation = null;

		try {
			negotiation = (Negotiation)manager.createQuery("from Negotiation where id = " + id).getSingleResult();
		} catch (Exception e) {
			throw new MyException();
		}

		return negotiation;
	}

	public void update(Negotiation negotiation) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.merge(negotiation);
		transaction.commit();
		manager.close();
	}

	public void delete(Negotiation negotiation) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		Negotiation entity = manager.merge(negotiation);
		manager.remove(entity);
		transaction.commit();
		manager.close();
	}
	
	public void delete(int id) throws MyException {
		delete(findById(id));
	}
}
