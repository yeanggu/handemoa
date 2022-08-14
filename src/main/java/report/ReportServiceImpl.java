package report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import adminreport.CommentReportDTO;
import adminreport.PostReportDTO;

@Service("reportservice")
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	@Qualifier("reportDAO")
	ReportDAO dao;

	@Override
	public int insertPostReport(PostReportDTO postreportdto) {
		return dao.insertPostReport(postreportdto);
	}

	@Override
	public int insertCommentReport(CommentReportDTO commentreportdto) {
		return dao.insertCommentReport(commentreportdto);
	}

}
