package adminmember;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

public interface AdminMemberService {
	
    public List<AdminMemberDTO> sellectalllist(PageDTO pagedto);//Paging 처리
    public int adminmembercount(PageDTO pagedto);//공지 총 갯수
  	public void deleteselect(String no); //공지 선택 삭제
  	public List<AdminMemberDTO> searchlist(SearchDTO search);//사용자 및 관리자 공지 리스트 검색
  	public int searchcount(SearchDTO search);//사용자 및 관리자 공지검색 총 갯수
  	public int quitselect(String no);//회원 선택 탍퇴
  	public List<String> quitmemberOX(Map<String,List<String>> list);//회원 탈퇴 여부
}