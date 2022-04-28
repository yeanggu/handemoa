package category1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("categoryservice")
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	@Qualifier("categorydao")
	CategoryDAO dao;

	@Override
	public List<CategoryDTO> categoryList(String catename) {
		return dao.categoryList(catename);
	}
	
	@Override
	public CategoryDTO catename(int catedetailcode) {
		return dao.catename(catedetailcode);
	}

}
