package report;

import adminreport.CommentReportDTO;
import adminreport.PostReportDTO;

public interface ReportService {

	//게시글 신고 등록
	public int insertPostReport(PostReportDTO postreportdto);
	
	//댓글 신고 등록
	public int insertCommentReport(CommentReportDTO commentreportdto);
}
