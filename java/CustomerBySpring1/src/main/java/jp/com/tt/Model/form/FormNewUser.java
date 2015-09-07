package jp.com.tt.model.form;

import javax.validation.constraints.Pattern;

public class FormNewUser {
	@Pattern(regexp="[0-9a-zA-Z]+")
	private String name;
	@Pattern(regexp="[0-9a-zA-Z]+")
	private String pass;
	@Pattern(regexp="[0-9a-zA-Z]+")
	private String pass2;
	
	public String getPass2() {
		return pass2;
	}
	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}
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
