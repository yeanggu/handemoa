package todo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TodoService {

	//디데이 목록 조회
	public List<TodoDTO> todoList(TodoDTO dto);
	
	public int inserttodo(TodoDTO insertvalue);
	
	public int updatetodo(int todostate, int todonum);
	
	public TodoDTO selecttodoicon(Integer todonum);
	
	public int updatetodoicon(int todostate, int todonum);
	
	public int updatetext(TodoDTO dto);
	
	public int deletetodo(Integer todonum);
}
