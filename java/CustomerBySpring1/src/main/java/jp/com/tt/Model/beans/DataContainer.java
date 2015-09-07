package jp.com.tt.model.beans;

public class DataContainer {
	private int id;
	private String text;
	
	public DataContainer(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
