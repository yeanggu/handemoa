package notice;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

public interface NoticeService {
	
	public List<NoticeDTO> noticelist();//공지 리스트
	public List<NoticeDTO> adminselectnotice();//관리자 공지 리스트
	public NoticeDTO noticeinner(int noticenum);//사용자,관리자 공지 조회
	public int insertnotice(NoticeDTO dto);//공지 등록
	public int deletenotice(int noticenum);//공지 삭제
	public NoticeDTO updateview(int noticenum);//공지 수정 view
	public int updatenotice(NoticeDTO dto);//공지 수정
    public List<NoticeDTO> sellectalllist(PageDTO pagedto);//Paging 처리
    public int noticecount();//공지 총 갯수
  	public void deleteselect(String no); //공지 선택 삭제
  	public List<NoticeDTO> searchlist(SearchDTO search);//사용자 및 관리자 공지 리스트 검색
  	public int searchcount(SearchDTO search);//사용자 및 관리자 공지검색 총 갯수
}