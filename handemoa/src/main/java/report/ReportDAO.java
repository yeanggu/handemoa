package report;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import adminreport.CommentReportDTO;
import adminreport.PostReportDTO;

@Mapper
@Repository("reportDAO")
public interface ReportDAO {
	//게시글 신고 등록
	public int insertPostReport(PostReportDTO postreportdto);
	
	//댓글 신고 등록
	public int insertCommentReport(CommentReportDTO commentreportdto);
}
