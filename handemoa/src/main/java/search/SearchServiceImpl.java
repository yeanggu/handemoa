package search;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import category1.CategoryDTO;

@Service("searchservice")
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	@Qualifier("searchDAO")
	SearchDAO dao;

	@Override
	public List<String> searchCate() {
		return dao.searchCate();
	}
	
	@Override
	public List<CategoryDTO> searchCateAll() {
		return dao.searchCateAll();
	}

	@Override
	public List<CategoryDTO> searchCateDetail(String catename) {
		return dao.searchCateDetail(catename);
	}

	@Override
	public List<SearchPostJoinDTO> searchPost(Map<String, Object> searchdetail) {
		return dao.searchPost(searchdetail);
	}

	@Override
	public int searchPostRows(Map<String, Object> searchdetail) {
		return dao.searchPostRows(searchdetail);
	}

}
