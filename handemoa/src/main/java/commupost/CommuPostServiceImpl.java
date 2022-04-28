package commupost;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import category1.CategoryDTO;
import rankpost.RankPostDTO;
import rankpost.RankPostVO;

@Service("commupostservice")
public class CommuPostServiceImpl implements CommuPostService {
	
	@Autowired
	CommuPostDAO dao;

	@Override
	public List<CommuPostVO> commuPostListView(String catedetailname) {
		return dao.commuPostListView(catedetailname);
	}
	@Override
	public List<CommuPostVO> commuPostListViewInt(int catedetailcode, int viewPage) {
		return dao.commuPostListViewInt(catedetailcode, viewPage);
	}
	
	@Override
	public List<CommuPostVO> commuPostListViewSearch(int catedetailcode, int viewPage, String postsearch) {
		return dao.commuPostListViewSearch(catedetailcode, viewPage, postsearch);
	}
	
	@Override
	public List<CommuPostVO> allCommuPostListViewInt(int catedetailcode) {
		return dao.allCommuPostListViewInt(catedetailcode);
	}
	
	@Override
	public List<CommuPostVO> allCommuPostListViewSearch(int catedetailcode, String postsearch) {
		return dao.allCommuPostListViewSearch(catedetailcode, postsearch);
	}

	@Override
	public List<CommuBoardVO> commuBoardList(String catedetailname) {
		return dao.commuBoardList(catedetailname);
	}
	@Override
	public List<CommuBoardVO> commuBoardListInt(int catedetailcode) {
		return dao.commuBoardListInt(catedetailcode);
	}
	
	@Override
	public CommuPostVO commuPostView(int postnum) {
		return dao.commuPostView(postnum);
	}
	
	@Override
	public List<CommuCommentVO> commucommentView(int postnum) {
		return dao.commucommentView(postnum);
	}
	/*
	@Override
	public int commuinsert(CommuPostDTO dto) {
		return dao.commuinsert(dto);
	}
	*/
	
	@Override
	public int commuinsert(CommuPostDTO dto) {
		int row = dao.commuinsert(dto);
		int postnum = dto.getPostnum();
		likecommupost(postnum,"admin",1);
		
		return row;
	}
	
	@Override
	public int commucommentInsert(CommuCommentVO vo) {
		return dao.commucommentInsert(vo);
	}
	
	@Override
	public int commuDeletepost(int postnum) {
		return dao.commuDeletepost(postnum);
	}
	
	// 커뮤니티 게시글 수정
	@Override
	public int communityupdate(CommuPostDTO dto) {
		return dao.communityupdate(dto);
	}

	// 커뮤니티 게시글 수정폼
	@Override
	public CommuPostDTO communityupdate2(int postnum) {
		return dao.communityupdate2(postnum);
	}
	
	// 커뮤 임시저장
	@Override
	public List<CommuPostVO> tempCommuPostListViewInt(int catedetailcode, int viewPage, int tempsave, String memberid) {
		return dao.tempCommuPostListViewInt(catedetailcode, viewPage, tempsave, memberid);
	}
	
	//  커뮤
	@Override
	public List<CommuPostVO> tempAllCommuPostListViewInt(int catedetailcode, int tempsave, String memberid) {
		// TODO Auto-generated method stub
		return dao.tempAllCommuPostListViewInt(catedetailcode, tempsave, memberid);
	}
	
	@Override
	public int likecommupost(int postnum, String userid, int likestatus) {
		return dao.likecommupost(postnum, userid, likestatus);
	}
	
	@Override
	public int countlikecommupost(int postnum, int likestatus) {
		return dao.countlikecommupost(postnum, likestatus);
	}
	
	@Override
	public void commuViewCountUp(int postnum) {
		dao.commuViewCountUp(postnum);
	}
	
}
