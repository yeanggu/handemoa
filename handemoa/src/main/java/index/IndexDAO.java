package index;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Repository;

import category1.CategoryDTO;
import index.IndexDTO;
import rankpost.RankBoardVO;

@Mapper
@Repository("indexdao")
public interface IndexDAO {
	
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


