package adminreport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminReportController {
	
	@Autowired
	@Qualifier("adminreportservice")
	AdminReportService service;
	
	//페이지 출력 행, 페이지 수 고정값
	private int limitRows = 8;
	private int limitPage = 5;

	@RequestMapping("/adminpostreport")
	public ModelAndView postReportList() {
		return postReportSearch(0, 1, 0, "");
	}

	//게시글 신고 조회
	@RequestMapping("/adminpostreport/search")
	public ModelAndView postReportSearch(int divisioncode, int currentpage, int search, String searchtxt) {
		ModelAndView mv = new ModelAndView();
		//divisioncode 0 전체 검색, 1 커뮤니티, 2 강의랭킹
		//search 0 전체 검색, 1 글제목, 2 닉네임
		//searchtxt 검색 text입력 값
					
		int pageStartRow = limitRows * ( currentpage - 1);
		
		Map<String, Object> searchdetail = new HashMap<String, Object>();
		searchdetail.put("divisioncode", divisioncode);
		searchdetail.put("search", search);
		searchdetail.put("searchtxt", searchtxt);
		searchdetail.put("pagestartrow", pageStartRow);
		searchdetail.put("limitrows", limitRows);
		

		List<PostReportJoinDTO> postReportList = service.searchPostReport(searchdetail);
		int postReporTotalRows = service.searchPostReportRows(searchdetail);
	
		
		AdminRCPageDTO reportpagedto = new AdminRCPageDTO(limitRows, limitPage, currentpage, postReporTotalRows);

		mv.addObject("divisioncode", divisioncode);
		mv.addObject("reportpagedto", reportpagedto);
		mv.addObject("postReportList", postReportList);
		mv.setViewName("adminreport/postreport");	
		return mv;
	}
	
	//게시글 신고 상세 내역 조회
	@ResponseBody
	@RequestMapping(value="/adminpostreport/detail", method=RequestMethod.POST)
	public List<PostReportDTO> postReportDetail(int postnum) {
		List<PostReportDTO> postReportDetail = service.postReportDetail(postnum);
		return postReportDetail;
	}
	
	//게시글 신고 삭제
	@ResponseBody
	@RequestMapping("/adminpostreport/delete")
	public int deletePostReport(@RequestParam(value="postnums[]" ) List<Integer> postnums) {
		int result = service.deletePostReport(postnums);
		return result;
	}
	
	//댓글 신고 내역 페이지
	@RequestMapping("/admincommentreport")
	public ModelAndView commentReportList() {
		return commentReportSearch(0, 1, 0, "");
	}

	//댓글 신고 조회
	@RequestMapping("/admincommentreport/search")
	public ModelAndView commentReportSearch(int divisioncode, int currentpage, int search, String searchtxt) {
		ModelAndView mv = new ModelAndView();
		//divisioncode 0 전체 검색, 1 커뮤니티, 2 강의랭킹
		//search 0 전체 검색, 1 댓글내용, 2 닉네임
		//searchtxt 검색 text입력 값
					
		int pageStartRow = limitRows * ( currentpage - 1);
		
		Map<String, Object> searchdetail = new HashMap<String, Object>();
		searchdetail.put("divisioncode", divisioncode);
		searchdetail.put("search", search);
		searchdetail.put("searchtxt", searchtxt);
		searchdetail.put("pagestartrow", pageStartRow);
		searchdetail.put("limitrows", limitRows);
		
		List<CommentReportJoinDTO> commentReportList = service.searchCommentReport(searchdetail);
		int commentReporTotalRows = service.searchCommentReportRows(searchdetail);	
	
		AdminRCPageDTO reportpagedto = new AdminRCPageDTO(limitRows, limitPage, currentpage, commentReporTotalRows);

		mv.addObject("divisioncode", divisioncode);
		mv.addObject("reportpagedto", reportpagedto);
		mv.addObject("commentReportList", commentReportList);
		mv.setViewName("adminreport/commentreport");	
		return mv;
	}
	//댓글 신고 상세 내역 조회
	@ResponseBody
	@RequestMapping(value="/admincommentreport/detail", method=RequestMethod.POST)
	public List<CommentReportDTO> commentReportDetail(int commentnum) {
		List<CommentReportDTO> commentReportDetail = service.commentReportDetail(commentnum);
		return commentReportDetail;
	}
	
	//댓글 신고 삭제
	@ResponseBody
	@RequestMapping("/admincommentreport/delete")
	public int deleteCommentReport(@RequestParam(value="commentnums[]" ) List<Integer> commentnums) {
		int result = service.deleteCommentReport(commentnums);
		return result;
	}
}
