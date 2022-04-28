package index;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;

import category1.CategoryDTO;
import rankpost.RankBoardVO;


@Service("indexservice")
public class IndexServiceImpl implements IndexService {


	@Autowired
	@Qualifier("indexdao")
	IndexDAO dao;
	
	@Override
	public List<String> searchCate() {
		return dao.searchCate();
	}
	
	@Override
	public List<IndexDTO> sellectreportlist(String reportType){
		return dao.sellectreportlist(reportType);
	}
	
	@Override
	public List<IndexDTO> sellectpostlist(String postType){
		return dao.sellectpostlist(postType);
	}
	
	@Override
	public List<IndexDTO> sellectnoticelist(){
		return dao.sellectnoticelist();
	}
	
	@Override
	public List<CategoryDTO> searchCateAll(String catename) {
		return dao.searchCateAll(catename);
	}

	@Override
	public List<CategoryDTO> searchCateDetail(String catename) {
		return dao.searchCateDetail(catename);
	}

	@Override
	public List<RankBoardVO> rankBoardListInt(int catedetailcode) {
		return dao.rankBoardListInt(catedetailcode);
	}

	
}
