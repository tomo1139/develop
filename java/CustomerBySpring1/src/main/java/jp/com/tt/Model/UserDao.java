package jp.com.tt.model;

import java.io.Serializable;
import java.util.List;

public interface UserDao <T> extends Serializable {
	public List<T> getAll();
	public T findById(int id);
	public void add(T data);
	public void update(T data);
	public void delete(T data);
	public void delete(int id);
}
