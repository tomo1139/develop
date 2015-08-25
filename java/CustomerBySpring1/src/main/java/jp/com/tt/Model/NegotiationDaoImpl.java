package jp.com.tt.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class NegotiationDaoImpl implements NegotiationDao<Negotiation> {
	private static EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("persistenceUnit");

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

	public Negotiation findById(int id) {
		EntityManager manager = factory.createEntityManager();
		Negotiation negotiation = (Negotiation)manager.createQuery("from Negotiation where id = " + id).getSingleResult();
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
	
	public void delete(int id) {
		delete(findById(id));
	}
}
