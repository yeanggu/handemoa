package notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;


@Service("noticeservice")
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	@Qualifier("noticedao")
	NoticeDAO dao;
	
	//공지 리스트
	@Override
	public List<NoticeDTO> noticelist() {
		return dao.noticelist();
	}

	//관리자 공지 리스트
	@Override
	public List<NoticeDTO> adminselectnotice() {
		return dao.adminselectnotice();
	}

	//사용자,관리자 공지 조회
	@Override
	public NoticeDTO noticeinner(int noticenum) {
		return dao.noticeinner(noticenum);
	}

	//공지 등록
	@Override
	public int insertnotice(NoticeDTO dto) {
		int insertrow = 0;
		
		if(dto.title.trim().isEmpty() || dto.content.trim().isEmpty()) {
			return insertrow;
		}else {
			return dao.insertnotice(dto);
		}
	}

	//공지 삭제
	@Override
	public int deletenotice(int noticenum) {
		return dao.deletenotice(noticenum);
	}

	//공지 수정 view
	@Override
	public NoticeDTO updateview(int noticenum) {
		return dao.updateview(noticenum);
	}

	//공지 수정
	@Override
	public int updatenotice(NoticeDTO dto) {
		int updaterow = 0;
		
		if(dto.title.trim().isEmpty() || dto.content.trim().isEmpty()) {
			return updaterow;
		}else {
			return dao.updatenotice(dto);
		}
		
	}
	
	//Paging 처리
	@Override
	public List<NoticeDTO> sellectalllist(PageDTO pagedto){
		return dao.sellectalllist(pagedto);
	}
	
	//공지 총 갯수
	@Override
	public int noticecount(){
		return dao.noticecount();
	}
	
	//공지 선택 삭제
	@Override
	public void deleteselect(String no) {
		dao.deleteselect(no);
	}
	
	//사용자 및 관리자 공지 리스트 검색
	@Override
	public List<NoticeDTO> searchlist(SearchDTO search) {
		return dao.searchlist(search);
	}
	
	//사용자 및 관리자 공지검색 총 갯수
	@Override
	public int searchcount(SearchDTO search) {
		return dao.searchcount(search);
	}

	
}
