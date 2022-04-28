package notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("noticedao")
public interface NoticeDAO {
	
	//공지 리스트
	public List<NoticeDTO> noticelist();
	
	//관리자 공지 리스트
	public List<NoticeDTO> adminselectnotice();

	//사용자 공지 조회
	public NoticeDTO noticeinner(int noticenum);
	
	//관리자 공지 조회
	public NoticeDTO adminnoticeinner(int noticenum);
	
	//공지 등록
	public int insertnotice(NoticeDTO dto);

	//공지 삭제
	public int deletenotice(int noticenum);
	
	//공지 수정 view
	public NoticeDTO updateview(int noticenum);
	
	//공지 수정
	public int updatenotice(NoticeDTO dto);
	
	//Paging처리
    public List<NoticeDTO> sellectalllist(PageDTO pagedto);
 
    //공지 총 갯수
    public int noticecount();
    
    //공지 선택 삭제
  	public void deleteselect(String no);
  	
  	//사용자 및 관리자 공지 리스트 검색
  	public List<NoticeDTO> searchlist(SearchDTO search);
  	
  	//사용자 및 관리자 공지검색 총 갯수
  	public int searchcount(SearchDTO search);


}


