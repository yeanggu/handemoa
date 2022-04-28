package search;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import admincomment.AdminCommentJoinDTO;
import category1.CategoryDTO;

@Mapper
@Repository("searchDAO")
public interface SearchDAO {

	//부분 카테고리 조회
	public List<String> searchCate();
	//세부 카테고리 조회
	public List<CategoryDTO> searchCateAll();
	//부분카테고리 해당 세부 카테고리 조회
	public List<CategoryDTO> searchCateDetail(String catename);
	
	//조건별 검색 게시글
	public List<SearchPostJoinDTO> searchPost(Map<String, Object> searchdetail);
	//검색된 게시글 리스트 수
	public int searchPostRows(Map<String, Object> searchdetail);
}
