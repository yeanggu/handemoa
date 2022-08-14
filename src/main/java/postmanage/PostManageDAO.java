package postmanage;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Repository;

import notice.NoticeDTO;

@Mapper
@Repository("postmanagedao")
public interface PostManageDAO {
	
	//Paging처리
    public List<PostManageDTO> sellectalllist(PageDTO pagedto);
 
    //공지 총 갯수
    public int postcount(PageDTO pagedto);
    
  	//사용자 및 관리자 공지 리스트 검색
  	public List<PostManageDTO> searchlist(SearchDTO search);
  	
  	//사용자 및 관리자 공지검색 총 갯수
  	public int searchcount(SearchDTO search);
  	
  	//회원 선택 탍퇴
	public int quitselect(int sample);
  	
	//사용자 공지 조회
	public PostManageDTO postinner(int postnum);
		
  	/////////////////////////////////////////////////////////////
  	
    //회원 선택 탍퇴
  	public int restorepost(int sample);
  	
	//Paging처리
    public List<PostManageDTO> sellectalllist2(PageDTO pagedto);
 
    //공지 총 갯수
    public int postcount2(PageDTO pagedto);
    
    //공지 선택 삭제
  	public int deleteselect(int sample);
  	
  	//사용자 및 관리자 공지 리스트 검색
  	public List<PostManageDTO> searchlist2(SearchDTO search);
  	
  	//사용자 및 관리자 공지검색 총 갯수
  	public int searchcount2(SearchDTO search);
}


