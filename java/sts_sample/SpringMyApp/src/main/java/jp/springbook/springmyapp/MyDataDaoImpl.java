package jp.springbook.springmyapp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MyDataDaoImpl implements MyDataDao<MyData> {
	private static EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("persistenceUnit");
	
	@Override
	public List<MyData> getAll() {
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("from MyData");
		List<MyData> list = query.getResultList();
		manager.close();
		return list;
	}
	
	@Override
	public void add(MyData data){
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(data);
		transaction.commit();
		manager.close();
	}	
}
