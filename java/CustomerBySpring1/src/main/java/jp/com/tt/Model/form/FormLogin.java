package jp.com.tt.model.form;

import javax.validation.constraints.Pattern;

public class FormLogin {
	@Pattern(regexp="[0-9a-zA-Z]+")
	private String name;
	@Pattern(regexp="[0-9a-zA-Z]+")
	private String pass;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
