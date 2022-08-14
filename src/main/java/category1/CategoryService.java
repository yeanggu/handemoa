package category1;

import java.util.List;

public interface CategoryService {
	
	public List<CategoryDTO> categoryList(String catename);
	
	public CategoryDTO catename(int catedetailcode);
}
