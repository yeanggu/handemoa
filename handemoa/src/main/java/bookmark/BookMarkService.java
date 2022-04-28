package bookmark;

import java.util.List;
import java.util.Map;

public interface BookMarkService {
	
	//북마크 조회
		public List<BookMarkJoinDTO> bookMarkList(Map<String, Object> searchdetail);
		public int bookMarkRows(Map<String, Object> searchdetail);
		//북마크 유무 확인
		public int checkBookMark(Map<String, Object> bookmark);
		//북마크 등록
		public int insertBookMark(BookMarkDTO dto);
		//북마크 삭제
		public int deleteBookMark(BookMarkDTO dto);
}
