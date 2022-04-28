package postmanage;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;


@Service("postmanageservice")
public class PostManageServiceImpl implements PostManageService {

	@Autowired
	@Qualifier("postmanagedao")
	PostManageDAO dao;
	
	//Paging 처리
	@Override
	public List<PostManageDTO> sellectalllist(PageDTO pagedto){
		return dao.sellectalllist(pagedto);
	}
	
	//공지 총 갯수
	@Override
	public int postcount(PageDTO pagedto){
		return dao.postcount(pagedto);
	}
	
	//사용자 및 관리자 공지 리스트 검색
	@Override
	public List<PostManageDTO> searchlist(SearchDTO search) {
		return dao.searchlist(search);
	}
	
	//사용자 및 관리자 공지검색 총 갯수
	@Override
	public int searchcount(SearchDTO search) {
		return dao.searchcount(search);
	}

	//회원 선택 탍퇴
	@Override
	public int quitselect(int sample) {
		return dao.quitselect(sample);
	}

	//사용자 공지 조회
	public PostManageDTO postinner(int postnum) {
		return dao.postinner(postnum);
	}
	
	/////////////////////////////////////////////////////////////
	  	
	//회원 선택 탍퇴
	public int restorepost(int sample) {
		return dao.restorepost(sample);
	}
	
	//Paging 처리
	@Override
	public List<PostManageDTO> sellectalllist2(PageDTO pagedto){
		return dao.sellectalllist2(pagedto);
	}
	
	//공지 총 갯수
	@Override
	public int postcount2(PageDTO pagedto){
		return dao.postcount2(pagedto);
	}
	
	//공지 선택 삭제
	@Override
	public int deleteselect(int sample) {
		return dao.deleteselect(sample);
	}
	
	//사용자 및 관리자 공지 리스트 검색
	@Override
	public List<PostManageDTO> searchlist2(SearchDTO search) {
		return dao.searchlist2(search);
	}
	
	//사용자 및 관리자 공지검색 총 갯수
	@Override
	public int searchcount2(SearchDTO search) {
		return dao.searchcount2(search);
	}

	
	
}
