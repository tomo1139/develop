package jp.com.tt.model.dao;

import java.io.Serializable;
import java.util.List;

import jp.com.tt.model.beans.Customer;

public interface CustomerDao <T> extends Serializable {
	public List<T> getAll();
	public T findById(int id) throws MyException;
	public List<T> getSearchAll(String id, String name, String period, String address, String postal);
	public void update(T data);
	public List<Customer> getSearchPartial(String id, String name, String period, String address, String postal, int i, int j, Integer retNum[]);
	public void add(T data);
	public Integer getNewestDataId();
}
