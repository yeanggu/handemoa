package rankpost;

import java.util.List;

import org.springframework.ui.Model;

import category1.CategoryDTO;

public interface RankPostService {

	public List<RankPostVO> rankPostListView(String catedetailname);
	public List<RankPostVO> rankPostListViewInt(int catedetailcode, int viewPage, int tempsave);
	
	public List<RankPostVO> tempRankPostListViewInt(int catedetailcode, int viewPage, int tempsave, String memberid);
	
	public List<RankPostVO> rankPostListViewSearch(int catedetailcode, int viewPage, String postsearch);
	
	public List<RankPostVO> allRankPostListViewInt(int catedetailcode, int tempsave);
	public List<RankPostVO> tempAllRankPostListViewInt(int catedetailcode, int tempsave, String memberid);
	public List<RankPostVO> allRankPostListViewSearch(int catedetailcode, String postsearch);
	
	List<RankBoardVO> rankBoardList(String catedetailname);
	List<RankBoardVO> rankBoardListInt(int catedetailcode);
	
	public RankPostVO rankPostView(int postnum);
	
	public List<CommentVO> commentView(int postnum);
		
	int insert(RankPostDTO dto);
	
	public int commentInsert(CommentVO vo);
	
	public int editRankingPost(RankPostDTO dto);
	
	public int postDelete(int postnum);
	
	public int likepost(int postnum, String userid, int likestatus);
	public int countlikepost(int postnum, int likestatus);
	
	public void rankingViewCountUp(int postnum);
	
	public int insertAlarm(AlarmDTO alarm);
	
	public List<AlarmDTO> alarmList(String id);
	public int alarmRead(int alarmnum);
	public int deleteAllAlarm(String id);
	
	
}
