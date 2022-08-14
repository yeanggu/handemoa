package adminreport;

import java.util.List;
import java.util.Map;

public interface AdminReportService {

	//조건별 게시글 신고 조회
	public List<PostReportJoinDTO> searchPostReport(Map<String, Object> searchdetail);
	//조건별 게시글 신고 수
	public int searchPostReportRows(Map<String, Object> searchdetail);

	//상세 조회, 삭제
	public List<PostReportDTO> postReportDetail(int postnum);
	public int deletePostReport(List<Integer> postnums);
	
	//조건별 댓글 신고 조회
	public List<CommentReportJoinDTO> searchCommentReport(Map<String, Object> searchdetail);
	//조건별 댓글 신고 수
	public int searchCommentReportRows(Map<String, Object> searchdetail);

	//상세 조회, 삭제
	public List<CommentReportDTO> commentReportDetail(int commentnum);
	public int deleteCommentReport(List<Integer> commentnums);
	
}
