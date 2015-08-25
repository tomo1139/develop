package jp.com.tt.model;

import java.io.Serializable;
import java.util.List;

public interface CustomerDao <T> extends Serializable {
	public List<T> getAll();
	public T findById(int id);
	public List<T> getSearchAll(String id, String name, String period, String address, String postal);
	public void update(T data);
}
