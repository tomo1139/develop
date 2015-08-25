package jp.com.tt.model;

import java.io.Serializable;
import java.util.List;

public interface NegotiationDao <T> extends Serializable {
	public List<T> getAll();
	public T findById(int id);
	public void add(T negotiation);
	public void delete(T negotiation);
	public void delete(int id);
}
