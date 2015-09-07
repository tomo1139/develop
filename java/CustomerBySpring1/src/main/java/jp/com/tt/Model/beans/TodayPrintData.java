package jp.com.tt.model.beans;

public class TodayPrintData {
	private int id;
	private int todayPk;
	private String name;
	private String address;
	private int m_flg;
	private String contractDate;

	public String getContractDate() {
		return contractDate;
	}
	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}
	public int getTodayPk() {
		return todayPk;
	}
	public void setTodayPk(int todayPk) {
		this.todayPk = todayPk;
	}
	public int getM_flg() {
		return m_flg;
	}
	public void setM_flg(int m_flg) {
		this.m_flg = m_flg;
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
