package jp.com.tt.model;

import java.io.Serializable;
import java.util.List;

public interface UserDao <T> extends Serializable {
	public List<T> getAll();
	public T findById(int id);
	public void add(T user);
	public void update(T user);
	public void delete(T user);
	public void delete(int id);
}
