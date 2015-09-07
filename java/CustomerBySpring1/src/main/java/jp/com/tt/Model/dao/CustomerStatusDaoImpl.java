package jp.com.tt.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jp.com.tt.model.beans.CustomerStatus;

public class CustomerStatusDaoImpl implements CustomerStatusDao<CustomerStatus> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("persistenceUnit");
	
	private static boolean flagInit = true;
	private static List<CustomerStatus> list = null;
	
	private void setList() {
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("from CustomerStatus");
		list = query.getResultList();
		manager.close();
	}

	public List<CustomerStatus> getAll() {
		if(flagInit) {
			flagInit = !flagInit;
			setList();
		}
		return list;
	}
	
	public CustomerStatus findById(int id) {
		if(flagInit) {
			flagInit = !flagInit;
			setList();
		}
		return list.get(id-1);
	}
}
