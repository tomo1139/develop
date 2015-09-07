package jp.com.tt.model.dao;

import java.io.Serializable;
import java.util.List;

public interface TodayDao <T> extends Serializable {
	public List<T> getAll();
	public List<T> getNotSameDay(String date);
	public List<T> getUserid(Integer id);
	public List<T> getUseridAndDay(Integer id, String date);
	public List<T> getCustomeridAndDay(Integer id, String date);
	public List<T> getUseridAndNotDay(Integer id, String date);
	public T findById(int id) throws MyException;
	public void add(T data);
	public void update(T data);
	public void delete(T data);
	public void delete(int id) throws MyException;
}
