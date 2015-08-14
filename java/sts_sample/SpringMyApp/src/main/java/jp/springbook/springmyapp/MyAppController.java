package jp.springbook.springmyapp;

import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyAppController {
		
	@RequestMapping(value = "/helo", method = RequestMethod.GET)
	public String helo(Model model) {
		model.addAttribute("title","Sample");
		model.addAttribute("message","MyDataのサンプルです。");
		MyData mydata = new MyData();
		model.addAttribute("myData", mydata);
		MyDataDao<MyData> dao = new MyDataDaoImpl();
		List<MyData> list = dao.getAll();
		model.addAttribute("datalist", list);
		return "showMessage";
	}

	@RequestMapping(value = "/helo", method = RequestMethod.POST)
	public String form(@Valid @ModelAttribute MyData mydata, Errors result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("title", "Sample [ERROR]");
			model.addAttribute("message", "値を再チェックしてください!");
			return "showMessage";
		}
		MyDataDao<MyData> dao = new MyDataDaoImpl();
		dao.add(mydata);
		return "redirect:/helo";
	}
	
}
