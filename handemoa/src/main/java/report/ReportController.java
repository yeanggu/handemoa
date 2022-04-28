package report;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import adminreport.CommentReportDTO;
import adminreport.PostReportDTO;

@Controller
public class ReportController {
	
	@Autowired
	@Qualifier("reportservice")
	ReportService service;
	
	//게시글 신고 등록
	@ResponseBody
	@RequestMapping(value="/postreport", method = RequestMethod.POST)
	public int postReportInsert(PostReportDTO dto) {

		//System.out.println("신고하는 회원 id" + dto.getReportid());

		int result = service.insertPostReport(dto);
		return result;
	}
	
	//댓글 신고 등록
	@ResponseBody
	@RequestMapping(value="/commentreport", method = RequestMethod.POST)
	public int commentReportInsert(CommentReportDTO dto, HttpServletRequest request) {
		
		//System.out.println("신고하는 회원 id" + dto.getReportid());
		
		int result = service.insertCommentReport(dto);
		return result;
	}

}
