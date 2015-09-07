package jp.com.tt.model.beans;

public class SearchPrintData {
	private int id;
	private String name;
	private String address;
	private int isToday;
	private String negotiateDay;
	private String negotiateName;

	public String getNegotiateDay() {
		return negotiateDay;
	}
	public void setNegotiateDay(String negotiateDay) {
		this.negotiateDay = negotiateDay;
	}
	public String getNegotiateName() {
		return negotiateName;
	}
	public void setNegotiateName(String negotiateName) {
		this.negotiateName = negotiateName;
	}
	public int getIsToday() {
		return isToday;
	}
	public void setIsToday(int isToday) {
		this.isToday = isToday;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
