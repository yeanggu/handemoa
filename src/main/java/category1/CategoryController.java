package category1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CategoryController {
	
	@Autowired
	@Qualifier("categoryservice")
	CategoryService service;
	
	int catedetailcode;
	
	@GetMapping("/catelist")
	@ResponseBody
	public List<CategoryDTO> catelist(String catename, Model model) {
		List<CategoryDTO> list = service.categoryList(catename);
		return list;
	}
	
	@GetMapping("/catename")
	@ResponseBody
	public CategoryDTO catename(int catedetailcode) {
		System.out.println("hi");
		CategoryDTO dto = service.catename(catedetailcode);
		System.out.println(dto.catedetailname);
		return dto;
	}

}
