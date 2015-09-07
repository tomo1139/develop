package jp.com.tt.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.com.tt.model.beans.User;
import jp.com.tt.model.dao.UserDao;
import jp.com.tt.model.dao.UserDaoImpl;
import jp.com.tt.model.form.FormChangePass;

@Controller
public class ChangePass {
	@RequestMapping(value = "/ChangePass", method = RequestMethod.GET)
	public String formGet(Model model, HttpSession session) {
		FormChangePass fm = new FormChangePass();
		model.addAttribute("formChangePass", fm);
		return "changePass";
	}

	@RequestMapping(value = "/ChangePass", method = RequestMethod.POST)
	public String formPost(@Valid @ModelAttribute FormChangePass fm, BindingResult result, Model model, HttpSession session) {

		if(result.hasErrors()) {
			model.addAttribute("changePassErrorMsg", "登録に失敗しました");
			return "changePass";
		}
		
		UserDao<User> dao = new UserDaoImpl();
		
		User user = (User) session.getAttribute("loginUser");

		String oldPassMD5 = DigestUtils.md5Hex(fm.getOldPass());
		if(!oldPassMD5.equals(user.getPass())) {
			model.addAttribute("changePassErrorMsg", "OLD PASSが違います");
			return "changePass";
		}
		
		if(!fm.getNewPass().equals(fm.getNewPass2())) {
			model.addAttribute("changePassErrorMsg", "NEW PASSが一致しません");
			return "changePass";
		}

		String newPassMD5 = DigestUtils.md5Hex(fm.getNewPass());
		user.setPass(newPassMD5);
		dao.update(user);
		session.setAttribute("loginUser", user);

		model.addAttribute("changePassSuccessMsg", "パスワードを変更しました");

		return "changePass";
	}
}
