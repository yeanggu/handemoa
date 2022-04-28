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
		return postReportPaging(0, 1);
	}
	
	//게시글 신고 분류별 조회
	@RequestMapping("/adminpostreport/division")
	public ModelAndView postReportPaging(int divisioncode, int currentpage) {
		ModelAndView mv = new ModelAndView();
		List<PostReportJoinDTO> postReportList;
		
		//페이지 버튼 클릭하면 page 값, 글 분류값 전송
		int postReporTotalRows = 0;			
					
		int pageStartRow = limitRows * ( currentpage - 1);
		
		//sql 조회 limit 설정 
		int[] page = {pageStartRow, limitRows};
		
		Map<String, Integer> division = new HashMap<String, Integer>();
		division.put("divisioncode", divisioncode);
		division.put("pagestartrow", pageStartRow);
		division.put("limitrows", limitRows);
		
		if(divisioncode == 0) {
			//전체검색
			postReportList = service.postReportList(page);
			postReporTotalRows = service.postReporTotalRows();
		}
		else { 
			//division별 조회
			postReportList = service.divisionPostReport(division);
			postReporTotalRows = service.postReportDivisionRows(divisioncode);
		}
		
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
		return commentReportPaging(0, 1);
	}
	
	//댓글 신고 분류별 조회
	@RequestMapping("/admincommentreport/division")
	public ModelAndView commentReportPaging(int divisioncode, int currentpage) {
		ModelAndView mv = new ModelAndView();
		List<CommentReportJoinDTO> commentReportList;
		System.out.println("divisioncode " + divisioncode);
		System.out.println("currentpage " + currentpage);
		//페이지 버튼 클릭하면 page 값, 글 분류값 전송
		int commentReporTotalRows = 0;			
					
		int pageStartRow = limitRows * ( currentpage - 1);
		
		int[] page = {pageStartRow, limitRows};
		
		Map<String, Integer> division = new HashMap<String, Integer>();
		division.put("divisioncode", divisioncode);
		division.put("pagestartrow", pageStartRow);
		division.put("limitrows", limitRows);
		
		if(divisioncode == 0) {
			//전체검색
			commentReportList = service.commentReportList(page);
			commentReporTotalRows = service.commentReporTotalRows();
		}
		else {
			commentReportList = service.divisionCommentReport(division);
			commentReporTotalRows = service.commentReportDivisionRows(divisioncode);
		}
		
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
