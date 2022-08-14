package calendar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("calendarservice")
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	@Qualifier("calendarDAO")
	CalendarDAO dao;

	@Override
	public List<CalDdayDTO> ddayList(String memberid) {
		return dao.ddayList(memberid);
	}

	@Override
	public List<CalOxDTO> oxnoteList(String memberid) {
		return dao.oxnoteList(memberid);
	}

	@Override
	public List<CalStudyDTO> studynoteList(String memberid) {
		return dao.studynoteList(memberid);
	}

	@Override
	public List<CalTodoDTO> todoList(String memberid) {
		return dao.todoList(memberid);
	}

}
