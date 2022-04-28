package commupost;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import category1.CategoryDTO;
import rankpost.RankPostVO;

@Mapper
@Repository
public interface CommuPostDAO {	
	
	public List<CommuPostVO> commuPostListView(String catedetailname);
	public List<CommuPostVO> commuPostListViewInt(int catedetailcode, int viewPage);
	
	public List<CommuPostVO> tempCommuPostListViewInt(int catedetailcode, int viewPage, int tempsave, String memberid);
	public List<CommuPostVO> tempAllCommuPostListViewInt(int catedetailcode, int tempsave, String memberid);
	
	public List<CommuPostVO> commuPostListViewSearch(int catedetailcode, int viewPage, String postsearch);
	
	public List<CommuPostVO> allCommuPostListViewInt(int catedetailcode);
	public List<CommuPostVO> allCommuPostListViewSearch(int catedetailcode, String postsearch);
	
	public List<CommuBoardVO> commuBoardList(String catedetailname);
	public List<CommuBoardVO> commuBoardListInt(int catedetailcode);	
	
	public CommuPostVO commuPostView(int postnum);
	
	public List<CommuCommentVO> commucommentView(int postnum);
		
	int commuinsert(CommuPostDTO dto);
	
	public int commucommentInsert(CommuCommentVO vo);
	
	public int commuDeletepost(int postnum);
	
	// 커뮤니티 게시글 수정
	public int communityupdate(CommuPostDTO dto);
		
		// 커뮤니티 게시글 수정폼
	public CommuPostDTO communityupdate2(int postnum);
	
	public int likecommupost(int postnum, String userid, int likestatus);
	public int countlikecommupost(int postnum, int likestatus);
}
