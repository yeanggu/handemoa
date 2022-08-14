package adminreport;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import admincomment.AdminCommentJoinDTO;

@Mapper
@Repository("adminreportDAO")
public interface AdminReportDAO {

	//게시판 신고관리
	//조건별 게시글 신고 조회
	public List<PostReportJoinDTO> searchPostReport(Map<String, Object> searchdetail);
	//조건별 게시글 신고 수
	public int searchPostReportRows(Map<String, Object> searchdetail);

	public List<PostReportDTO> postReportDetail(int postnum);
	public int deletePostReport(List<Integer> postnums);
	
	//댓글 신고관리
	//조건별 댓글 신고 조회
	public List<CommentReportJoinDTO> searchCommentReport(Map<String, Object> searchdetail);
	//조건별 댓글 신고 수
	public int searchCommentReportRows(Map<String, Object> searchdetail);

	public List<CommentReportDTO> commentReportDetail(int commentnums);
	public int deleteCommentReport(List<Integer> commentnums);
	
}
