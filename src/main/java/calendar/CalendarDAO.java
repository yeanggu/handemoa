package calendar;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("calendarDAO")
public interface CalendarDAO {
	
	//디데이 목록 조회
	public List<CalDdayDTO> ddayList(String memberid);
	//oxnote 목록 조회
	public List<CalOxDTO> oxnoteList(String memberid);
	//studynote 목록 조회
	public List<CalStudyDTO> studynoteList(String memberid);
	//todo 목록 조회
	public List<CalTodoDTO> todoList(String memberid);

}
