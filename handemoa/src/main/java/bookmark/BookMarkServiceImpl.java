package bookmark;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("bookmarkservice")
public class BookMarkServiceImpl implements BookMarkService{
	
	@Autowired
	@Qualifier("bookMarkDAO")
	BookMarkDAO dao;

	@Override
	public List<BookMarkJoinDTO> bookMarkList(Map<String, Object> searchdetail) {
		return dao.bookMarkList(searchdetail);
	}

	@Override
	public int bookMarkRows(Map<String, Object> searchdetail) {
		return dao.bookMarkRows(searchdetail);
	}

	@Override
	public int checkBookMark(Map<String, Object> bookmark) {
		return dao.checkBookMark(bookmark);
	}

	@Override
	public int insertBookMark(BookMarkDTO dto) {
		return dao.insertBookMark(dto);
	}

	@Override
	public int deleteBookMark(BookMarkDTO dto) {
		return dao.deleteBookMark(dto);
	}



	
}
