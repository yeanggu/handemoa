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
	AdminReportDAO adminreportDAO;

	@Override
	public List<PostReportJoinDTO> postReportList(int[] page) {
		return adminreportDAO.postReportList(page);
	}

	@Override
	public List<PostReportJoinDTO> divisionPostReport(Map<String, Integer> division) {
		return adminreportDAO.divisionPostReport(division);
	}

	@Override
	public int postReporTotalRows() {
		return adminreportDAO.postReporTotalRows();
	}

	@Override
	public int postReportDivisionRows(int divisioncode) {
		return adminreportDAO.postReportDivisionRows(divisioncode);
	}

	@Override
	public List<PostReportDTO> postReportDetail(int postnum) {
		return adminreportDAO.postReportDetail(postnum);
	}

	@Override
	public int deletePostReport(List<Integer> postnums) {
		return adminreportDAO.deletePostReport(postnums);
	}

	
	@Override
	public List<CommentReportJoinDTO> commentReportList(int[] page) {
		return adminreportDAO.commentReportList(page);
	}

	@Override
	public List<CommentReportJoinDTO> divisionCommentReport(Map<String, Integer> division) {
		return adminreportDAO.divisionCommentReport(division);
	}

	@Override
	public int commentReporTotalRows() {
		return adminreportDAO.commentReporTotalRows();
	}

	@Override
	public int commentReportDivisionRows(int divisioncode) {
		return adminreportDAO.commentReportDivisionRows(divisioncode);
	}

	@Override
	public List<CommentReportDTO> commentReportDetail(int commentnum) {
		return adminreportDAO.commentReportDetail(commentnum);
	}

	@Override
	public int deleteCommentReport(List<Integer> commentnums) {
		return adminreportDAO.deleteCommentReport(commentnums);
	}
	
	
}
