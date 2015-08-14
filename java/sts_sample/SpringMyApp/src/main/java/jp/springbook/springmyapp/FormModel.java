package jp.springbook.springmyapp;

import java.util.Date;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotEmpty;

public class FormModel {
	
	@NotEmpty
	private String item;
	@Min(value=0)
	private Integer price;
	private Date buydate;
	@Phone(onlyNumber=true)
	private String memo;
	
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public Date getBuydate() {
		return buydate;
	}
	public void setBuydate(Date buydate) {
		this.buydate = buydate;
	}
	
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
