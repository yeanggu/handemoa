package admincomment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import adminreport.AdminReportService;
import adminreport.PostReportJoinDTO;
import adminreport.AdminRCPageDTO;

@Controller
public class AdminCommentController {
	
	@Autowired
	@Qualifier("admincommentservice")
	AdminCommentService service;
	
	//페이지 출력 행, 페이지 수 고정값
	private int limitRows = 8;
	private int limitPage = 5;
	
	@RequestMapping("/admincomment")
	public ModelAndView commentList() {
		//첫 조회 화면
		return searchComment(0, 1, 0, "");
	}
	
	//댓글 분류별 조회
	@RequestMapping("/admincomment/search")
	public ModelAndView searchComment(int divisioncode, int currentpage, int search, String searchtxt) {
		
		//divisioncode 0 전체 검색, 1 커뮤니티, 2 강의랭킹
		//search 0 전체 검색, 1 댓글번호, 2 댓글내용, 3 닉네임
		//searchtxt 검색 text입력 값		

		//페이징
		int pageStartRow = limitRows * ( currentpage - 1);
		
		Map<String, Object> searchdetail = new HashMap<String, Object>();
		searchdetail.put("divisioncode", divisioncode);
		searchdetail.put("search", search);
		searchdetail.put("searchtxt", searchtxt);
		searchdetail.put("pagestartrow", pageStartRow);
		searchdetail.put("limitrows", limitRows);

		//조건 별 댓글 조회
		List<AdminCommentJoinDTO> commentList = service.searchComment(searchdetail);
		int searchCommentRows = service.searchCommentRows(searchdetail);					
		
		AdminRCPageDTO pagedto = new AdminRCPageDTO(limitRows, limitPage, currentpage, searchCommentRows);

		ModelAndView mv = new ModelAndView();		
		mv.addObject("divisioncode", divisioncode);
		mv.addObject("searchdetail", searchdetail);
		mv.addObject("pagedto", pagedto);
		mv.addObject("commentList", commentList);
		mv.setViewName("admincomment/admincomment");	
		return mv;
	}
	
	//댓글 삭제
	@ResponseBody
	@RequestMapping("/admincomment/delete")
	public int deleteComment(@RequestParam("commentnums[]" ) List<Integer> commentnums) {
		int result = service.deleteComment(commentnums);
		return result;
	}
}
