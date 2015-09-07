package jp.com.tt.model.dao;

import java.io.Serializable;
import java.util.List;

public interface CustomerMeansDao <T> extends Serializable {
	public List<T> getAll();
	public T findById(int id);
}
