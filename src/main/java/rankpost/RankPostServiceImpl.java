package rankpost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import category1.CategoryDTO;

@Service("rankpostservice")
public class RankPostServiceImpl implements RankPostService {
	
	@Autowired
	RankPostDAO dao;

	@Override
	public List<RankPostVO> rankPostListView(String catedetailname) {
		return dao.rankPostListView(catedetailname);
	}
	@Override
	public List<RankPostVO> rankPostListViewInt(int catedetailcode, int viewPage, int tempsave) {
		return dao.rankPostListViewInt(catedetailcode, viewPage, tempsave);
	}
	
	@Override
	public List<RankPostVO> tempRankPostListViewInt(int catedetailcode, int viewPage, int tempsave, String memberid) {
		return dao.tempRankPostListViewInt(catedetailcode, viewPage, tempsave, memberid);
	}
	
	@Override
	public List<RankPostVO> rankPostListViewSearch(int catedetailcode, int viewPage, String postsearch) {
		return dao.rankPostListViewSearch(catedetailcode, viewPage, postsearch);
	}
	
	@Override
	public List<RankPostVO> allRankPostListViewInt(int catedetailcode, int tempsave) {
		return dao.allRankPostListViewInt(catedetailcode, tempsave);
	}
	
	@Override
	public List<RankPostVO> tempAllRankPostListViewInt(int catedetailcode, int tempsave, String memberid) {
		// TODO Auto-generated method stub
		return dao.tempAllRankPostListViewInt(catedetailcode, tempsave, memberid);
	}
	
	@Override
	public List<RankPostVO> allRankPostListViewSearch(int catedetailcode, String postsearch) {
		return dao.allRankPostListViewSearch(catedetailcode, postsearch);
	}

	@Override
	public List<RankBoardVO> rankBoardList(String catedetailname) {
		return dao.rankBoardList(catedetailname);
	}
	@Override
	public List<RankBoardVO> rankBoardListInt(int catedetailcode) {
		return dao.rankBoardListInt(catedetailcode);
	}
	
	@Override
	public RankPostVO rankPostView(int postnum) {
		return dao.rankPostView(postnum);
	}
	
	@Override
	public List<CommentVO> commentView(int postnum) {
		return dao.commentView(postnum);
	}
	
	@Override
	public int insert(RankPostDTO dto) {
		int row = dao.insert(dto);
		int postnum = dto.getPostnum();
		likepost(postnum,"admin",1);
		
		return row;
	}
	
	@Override
	public int commentInsert(CommentVO vo) {
		return dao.commentInsert(vo);
	}
	
	@Override
	public int editRankingPost(RankPostDTO dto) {
		return dao.editRankingPost(dto);
	}
	
	@Override
	public int postDelete(int postnum) {
		return dao.postDelete(postnum);
	}
	
	@Override
	public int likepost(int postnum, String userid, int likestatus) {
		return dao.likepost(postnum, userid, likestatus);
	}
	
	@Override
	public int countlikepost(int postnum, int likestatus) {
		return dao.countlikepost(postnum, likestatus);
	}
	
	@Override
	public void rankingViewCountUp(int postnum) {
		dao.rankingViewCountUp(postnum);
	}
	
	@Override
	public int insertAlarm(AlarmDTO alarm) {
		return dao.insertAlarm(alarm);
	}
	
	@Override
	public List<AlarmDTO> alarmList(String id) {
		return dao.alarmList(id);
	}
	
	@Override
	public int alarmRead(int alarmnum) {
		return dao.alarmRead(alarmnum);
	}
	
	@Override
	public int deleteAllAlarm(String id) {
		return dao.deleteAllAlarm(id);
	}
}
