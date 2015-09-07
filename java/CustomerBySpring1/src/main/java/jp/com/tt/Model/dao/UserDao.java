package jp.com.tt.model.dao;

import java.io.Serializable;
import java.util.List;

import jp.com.tt.model.beans.User;

public interface UserDao <T> extends Serializable {
	public List<T> getAll();
	public T findById(int id);
	public List<User> findByName(String name);
	public void add(T data);
	public void update(T data);
	public void delete(T data);
	public void delete(int id);
}
