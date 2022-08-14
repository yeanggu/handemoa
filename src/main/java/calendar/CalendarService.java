package calendar;

import java.util.List;

public interface CalendarService {

	//디데이 목록 조회
	public List<CalDdayDTO> ddayList(String memberid);
	//oxnote 목록 조회
	public List<CalOxDTO> oxnoteList(String memberid);
	//studynote 목록 조회
	public List<CalStudyDTO> studynoteList(String memberid);
	//todo 목록 조회
	public List<CalTodoDTO> todoList(String memberid);

}
