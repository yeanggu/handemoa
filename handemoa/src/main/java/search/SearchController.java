package search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bookmark.ViewPageDTO;
import category1.CategoryDTO;

@Controller
public class SearchController {
	
	@Autowired
	@Qualifier("searchservice")
	SearchService service;
	
	//페이지 출력 행, 페이지 수 고정값
	private int limitRows = 8;
	private int limitPage = 5;
	
	@RequestMapping("/search")
	public ModelAndView search(String searchtxt) {
		ModelAndView mv = searchdetail(0, 1, "전체검색", 0, searchtxt);	
		
		List<CategoryDTO> cateAll = service.searchCateAll();
		
		mv.addObject("cateAll", cateAll);
		mv.addObject("catename", category());
		mv.setViewName("search/search");
		return mv;
	}
	
	//카테고리 불러오기
	//처음에는 전체 카테고리 출력
	//부분카테고리
	public List<String> category() {
		List<String> catename = service.searchCate();
		return catename;
	}
	
	//ajax로 부분카테고리 별로 세부카테고리 출력	
	@PostMapping("/selectcate")
	@ResponseBody
	public List<CategoryDTO> catedetail(String catename) {
		List<CategoryDTO> catedetail = service.searchCateDetail(catename);
		return catedetail;
	}

	@RequestMapping("/searchdetail")
	public ModelAndView searchdetail(int divisioncode, int currentpage, String catename, int catedetailcode, String searchtxt) {
		
		//divisioncode 0 전체 검색, 1 커뮤니티, 2 강의랭킹
		//catename 전체검색, catename값
		//catedetailcode 0 전체 검색
		//searchtxt 검색 text입력 값		

		//페이징
		int pageStartRow = limitRows * ( currentpage - 1);
		Map<String, Object> searchdetail = new HashMap<String, Object>();
		searchdetail.put("divisioncode", divisioncode);
		searchdetail.put("catename", catename);
		searchdetail.put("catedetailcode", catedetailcode);
		searchdetail.put("searchtxt", searchtxt);
		searchdetail.put("pagestartrow", pageStartRow);
		searchdetail.put("limitrows", limitRows);
		
		//조건 별 게시글 조회
		List<SearchPostJoinDTO> searchPostList = service.searchPost(searchdetail);
		int searchPostRows = service.searchPostRows(searchdetail);					
		
		ViewPageDTO pagedto = new ViewPageDTO(limitRows, limitPage, currentpage, searchPostRows);	

		ModelAndView mv = new ModelAndView();
		mv.addObject("catename", category());
		mv.addObject("divisioncode", divisioncode);
		mv.addObject("searchdetail", searchdetail);
		mv.addObject("pagedto", pagedto);
		mv.addObject("searchPostList", searchPostList);
		mv.setViewName("search/search");	
		return mv;
	}
}
