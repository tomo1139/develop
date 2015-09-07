package jp.com.tt.controller;

import java.util.List;

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
import jp.com.tt.model.form.FormNewUser;

@Controller
public class NewUser {
	@RequestMapping(value = "/NewUser", method = RequestMethod.GET)
	public String formGet(Model model, HttpSession session) {
		FormNewUser fm = new FormNewUser();
		model.addAttribute("formNewUser", fm);
		return "newUser";
	}

	@RequestMapping(value = "/NewUser", method = RequestMethod.POST)
	public String formPost(@Valid @ModelAttribute FormNewUser fm, BindingResult result,Model model, HttpSession session) {

		if(result.hasErrors()) {
			model.addAttribute("registErrorMsg", "登録に失敗しました");
			return "newUser";
		}

		if(fm.getPass() == "" || fm.getPass2() == "") {
			model.addAttribute("registErrorMsg", "登録に失敗しました");
			return "newUser";
		}
		
		if(!fm.getPass().equals(fm.getPass2())){
			model.addAttribute("registErrorMsg", "パスワードが一致しません");
			return "newUser";
		}
		
		UserDao<User> dao = new UserDaoImpl();
		List<User> list = dao.findByName(fm.getName());
		
		if(list.size() != 0) {
			model.addAttribute("registErrorMsg", "登録済みユーザーです");
			return "newUser";
		}

		String fmMd5pass = DigestUtils.md5Hex(fm.getPass());
		User user = new User();
		user.setName(fm.getName());
		user.setPass(fmMd5pass);
		dao.add(user);

		model.addAttribute("registSuccessMsg", "登録に成功しました");
		return "newUser";
	}
}
