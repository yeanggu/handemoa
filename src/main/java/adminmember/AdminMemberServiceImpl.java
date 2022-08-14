package adminmember;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;


@Service("adminmemberservice")
public class AdminMemberServiceImpl implements AdminMemberService {

	@Autowired
	@Qualifier("adminmemberdao")
	AdminMemberDAO dao;
	
	//Paging 처리
	@Override
	public List<AdminMemberDTO> sellectalllist(PageDTO pagedto){
		return dao.sellectalllist(pagedto);
	}
	
	//공지 총 갯수
	@Override
	public int adminmembercount(PageDTO pagedto){
		return dao.adminmembercount(pagedto);
	}
	
	//공지 선택 삭제
	@Override
	public void deleteselect(String no) {
		dao.deleteselect(no);
	}
	
	//사용자 및 관리자 공지 리스트 검색
	@Override
	public List<AdminMemberDTO> searchlist(SearchDTO search) {
		return dao.searchlist(search);
	}
	
	//사용자 및 관리자 공지검색 총 갯수
	@Override
	public int searchcount(SearchDTO search) {
		return dao.searchcount(search);
	}
	
	//회원 선택 탍퇴
	@Override
	public int updatepost(String no) {
		return dao.updatepost(no);
	}
	
	//회원 선택 탍퇴
	@Override
	public int updatecomment(String no) {
		return dao.updatecomment(no);
	}
	
	//회원 선택 탍퇴
	@Override
	public int updatecommentreport(String no) {
		return dao.updatecommentreport(no);
	}
		
	//회원 선택 탍퇴
	@Override
	public int updatepostreport(String no) {
		return dao.updatepostreport(no);
	}
	
	
	//회원 선택 탍퇴
	@Override
	public int quitselect(String no) {
		return dao.quitselect(no);
	}

	@Override
	public List<String> quitmemberOX(Map<String,List<String>> list) {
		return dao.quitmemberOX(list);
	}
	
	
}
