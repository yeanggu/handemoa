package adminmember;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Repository;

import notice.NoticeDTO;

@Mapper
@Repository("adminmemberdao")
public interface AdminMemberDAO {
	
	//Paging처리
    public List<AdminMemberDTO> sellectalllist(PageDTO pagedto);
 
    //공지 총 갯수
    public int adminmembercount(PageDTO pagedto);
    
    //공지 선택 삭제
  	public void deleteselect(String no);
  	
  	//사용자 및 관리자 공지 리스트 검색
  	public List<AdminMemberDTO> searchlist(SearchDTO search);
  	
  	//사용자 및 관리자 공지검색 총 갯수
  	public int searchcount(SearchDTO search);
  	
  	
    //회원 선택 탍퇴
  	public int updatepost(String no);
  	
  //회원 선택 탍퇴
  	public int updatecomment(String no);
  	
  	 //회원 선택 탍퇴
  	public int updatecommentreport(String no);
  	
  	 //회원 선택 탍퇴
  	public int updatepostreport(String no);
  	
  	//회원 선택 탍퇴
  	public int quitselect(String no);
  	
  	//회원 탈퇴 여부
  	public List<String> quitmemberOX(Map<String,List<String>> list);
  	
}


