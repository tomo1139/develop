package jp.com.tt.model.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="management_customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int id;
	
	@Column(length=32, nullable=false)
	private String name;
	
	@Column(length=32, nullable=false)
	private String address;

	@Column(length=15, nullable=false)
	private String postal;
	
	@Column(length=15, nullable=false)
	private String home_phone;
	
	@Column(length=15, nullable=false)
	private String mobile_phone;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String management_type;
	
	@Column(nullable=false)
	private String contract_date;
	
	@Column(nullable=false)
	private String payment_method;
	
	@Column(nullable=false)
	private String payment_course;

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

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getHome_phone() {
		return home_phone;
	}

	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
	}

	public String getMobile_phone() {
		return mobile_phone;
	}

	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getManagement_type() {
		return management_type;
	}

	public void setManagement_type(String management_type) {
		this.management_type = management_type;
	}

	public String getContract_date() {
		return contract_date;
	}

	public void setContract_date(String contract_date) {
		this.contract_date = contract_date;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getPayment_course() {
		return payment_course;
	}

	public void setPayment_course(String payment_course) {
		this.payment_course = payment_course;
	}

}
