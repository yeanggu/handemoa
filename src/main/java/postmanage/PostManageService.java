package postmanage;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

public interface PostManageService {
	
    public List<PostManageDTO> sellectalllist(PageDTO pagedto);//Paging 처리
    public int postcount(PageDTO pagedto);//공지 총 갯수
  	public List<PostManageDTO> searchlist(SearchDTO search);//사용자 및 관리자 공지 리스트 검색
  	public int searchcount(SearchDTO search);//사용자 및 관리자 공지검색 총 갯수
	public int quitselect(int sample);//회원 선택 탍퇴
	
	public PostManageDTO postinner(int postnum);//사용자 공지 조회
  	
  	
  	
	/////////////////////////////////////////////////////////////
	  	
	
	public int restorepost(int sample);//회원 선택 탍퇴
    public List<PostManageDTO> sellectalllist2(PageDTO pagedto);//Paging 처리
    public int postcount2(PageDTO pagedto);//공지 총 갯수
  	public int deleteselect(int sample); //공지 선택 삭제
  	public List<PostManageDTO> searchlist2(SearchDTO search);//사용자 및 관리자 공지 리스트 검색
  	public int searchcount2(SearchDTO search);//사용자 및 관리자 공지검색 총 갯수
}