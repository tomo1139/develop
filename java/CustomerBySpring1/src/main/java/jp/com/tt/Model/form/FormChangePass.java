package jp.com.tt.model.form;

import javax.validation.constraints.Pattern;

public class FormChangePass {
	@Pattern(regexp="[0-9a-zA-Z]+")
	private String oldPass;
	@Pattern(regexp="[0-9a-zA-Z]+")
	private String newPass;
	@Pattern(regexp="[0-9a-zA-Z]+")
	private String newPass2;

	public String getOldPass() {
		return oldPass;
	}
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	public String getNewPass2() {
		return newPass2;
	}
	public void setNewPass2(String newPass2) {
		this.newPass2 = newPass2;
	}
}
