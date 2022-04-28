package category1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("categorydao")
public interface CategoryDAO {
	
	public List<CategoryDTO> categoryList(String catename);

	public CategoryDTO catename(int catedetailcode);
}
