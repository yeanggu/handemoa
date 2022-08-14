package index;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

import category1.CategoryDTO;
import rankpost.RankBoardVO;

public interface IndexService {
	
	public List<String> searchCate();
    public List<IndexDTO> sellectreportlist(String reportType);
    public List<IndexDTO> sellectpostlist(String postType);
    public List<IndexDTO> sellectnoticelist();
    
	//세부 카테고리 조회
	public List<CategoryDTO> searchCateAll(String catename);
	//부분카테고리 해당 세부 카테고리 조회
	public List<CategoryDTO> searchCateDetail(String catename);
	
	
	
	List<RankBoardVO> rankBoardListInt(int catedetailcode);
}