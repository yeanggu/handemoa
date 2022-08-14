package adminreport;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("adminreportservice")
public class AdminReportServiceImpl implements AdminReportService {

	@Autowired
	@Qualifier("adminreportDAO")
	AdminReportDAO dao;

	//게시글 신고
	@Override
	public List<PostReportJoinDTO> searchPostReport(Map<String, Object> searchdetail) {
		return dao.searchPostReport(searchdetail);
	}

	@Override
	public int searchPostReportRows(Map<String, Object> searchdetail) {
		return dao.searchPostReportRows(searchdetail);
	}

	@Override
	public List<PostReportDTO> postReportDetail(int postnum) {
		return dao.postReportDetail(postnum);
	}

	@Override
	public int deletePostReport(List<Integer> postnums) {
		return dao.deletePostReport(postnums);
	}

	//댓글 신고
	@Override
	public List<CommentReportJoinDTO> searchCommentReport(Map<String, Object> searchdetail) {
		return dao.searchCommentReport(searchdetail);
	}

	@Override
	public int searchCommentReportRows(Map<String, Object> searchdetail) {
		return dao.searchCommentReportRows(searchdetail);
	}	
	
	@Override
	public List<CommentReportDTO> commentReportDetail(int commentnum) {
		return dao.commentReportDetail(commentnum);
	}
	
	@Override
	public int deleteCommentReport(List<Integer> commentnums) {
		return dao.deleteCommentReport(commentnums);
	}

}
