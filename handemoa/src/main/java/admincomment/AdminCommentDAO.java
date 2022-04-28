package admincomment;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository("admincommentDAO")
public interface AdminCommentDAO {
	//조건별 댓글 페이징 조회
	public List<AdminCommentJoinDTO> searchComment(Map<String, Object> searchdetail);
	//조건별 댓글 수
	public int searchCommentRows(Map<String, Object> searchdetail);
	public int deleteComment(List<Integer> commentnum);

}
