package jp.com.tt.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jp.com.tt.model.beans.CustomerMeans;
import jp.com.tt.model.beans.CustomerStatus;

public class CustomerMeansDaoImpl implements CustomerMeansDao<CustomerMeans> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("persistenceUnit");

	private static boolean flagInit = true;
	private static List<CustomerMeans> list = null;
	
	private void setList() {
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("from CustomerMeans");
		list = query.getResultList();
		manager.close();
	}

	public List<CustomerMeans> getAll() {
		if(flagInit) {
			flagInit = !flagInit;
			setList();
		}
		return list;
	}
	
	public CustomerMeans findById(int id) {
		if(flagInit) {
			flagInit = !flagInit;
			setList();
		}
		return list.get(id-1);
	}
}
