package adminreport;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("adminreportDAO")
public interface AdminReportDAO {

	//게시판 신고관리
	public List<PostReportJoinDTO> postReportList(int[] page);
	public List<PostReportJoinDTO> divisionPostReport(Map<String, Integer> division);
	public int postReporTotalRows();
	public int postReportDivisionRows(int divisioncode);
	public List<PostReportDTO> postReportDetail(int postnum);
	public int deletePostReport(List<Integer> postnums);
	
	//댓글 신고관리
	public List<CommentReportJoinDTO> commentReportList(int[] page);
	public List<CommentReportJoinDTO> divisionCommentReport(Map<String, Integer> division);
	public int commentReporTotalRows();
	public int commentReportDivisionRows(int divisioncode);
	public List<CommentReportDTO> commentReportDetail(int commentnums);
	public int deleteCommentReport(List<Integer> commentnums);
	
}
