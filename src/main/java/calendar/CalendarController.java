package calendar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/handemore/calendar")
public class CalendarController {
	
	@Autowired
	@Qualifier("calendarservice")
	CalendarService service;
	
	//dday 목록 조회	
	@GetMapping("/dday/{memberid}")
	public List<CalDdayDTO> getDdayList(@PathVariable("memberid")String memberid) {
		
		List<CalDdayDTO> ddays = service.ddayList(memberid);
		
		return ddays;
	}
	//oxnote 목록 조회
	@GetMapping("/ox/{memberid}")
	public List<CalOxDTO> getOXList(@PathVariable("memberid")String memberid) {
		
		List<CalOxDTO> oxnotes = service.oxnoteList(memberid);
		
		return oxnotes;
	}
	//studynote 목록 조회
	@GetMapping("/study/{memberid}")
	public List<CalStudyDTO> getStudyList(@PathVariable("memberid")String memberid) {
		
		List<CalStudyDTO> studynotes = service.studynoteList(memberid);
		
		return studynotes;
	}
	
	//todo 목록 조회
	@GetMapping("/todo/{memberid}")
	public List<CalTodoDTO> getTodoList(@PathVariable("memberid")String memberid) {
		
		List<CalTodoDTO> todos = service.todoList(memberid);
		
		return todos;
	}
}
