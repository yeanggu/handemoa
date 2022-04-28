package adminreport;

import java.util.List;
import java.util.Map;

public interface AdminReportService {

	//게시글 신고기록 조회, 카테고리별 조회, 전체 레코드 수, 카테고리별 레코드 수
	//상세 조회, 삭제
	public List<PostReportJoinDTO> postReportList(int[] page);
	public List<PostReportJoinDTO> divisionPostReport(Map<String, Integer> division);
	public int postReporTotalRows();
	public int postReportDivisionRows(int divisioncode);
	public List<PostReportDTO> postReportDetail(int postnum);
	public int deletePostReport(List<Integer> postnums);
	
	//댓글 신고관리 조회, 카테고리별 조회, 전체 레코드 수, 카테고리별 레코드 수
	//상세 조회, 삭제
	public List<CommentReportJoinDTO> commentReportList(int[] page);
	public List<CommentReportJoinDTO> divisionCommentReport(Map<String, Integer> division);
	public int commentReporTotalRows();
	public int commentReportDivisionRows(int divisioncode);
	public List<CommentReportDTO> commentReportDetail(int commentnum);
	public int deleteCommentReport(List<Integer> commentnums);
	
}
