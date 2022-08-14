package admincomment;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("admincommentservice")
public class AdminCommentServiceImpl implements AdminCommentService {
	
	@Autowired
	@Qualifier("admincommentDAO")
	AdminCommentDAO dao;

	@Override
	public List<AdminCommentJoinDTO> searchComment(Map<String, Object> searchdetail) {
		return dao.searchComment(searchdetail);
	}

	@Override
	public int searchCommentRows(Map<String, Object> searchdetail) {
		return dao.searchCommentRows(searchdetail);
	}

	@Override
	public int deleteComment(List<Integer> commentnum) {
		return dao.deleteComment(commentnum);
	}


}
