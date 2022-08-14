package todo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service("todoservice")
public class TodoServiceImpl implements TodoService {

	@Autowired
	@Qualifier("todoDAO")
	TodoDAO dao;
	
	@Override
	public List<TodoDTO> todoList(TodoDTO dto) {
		return dao.todoList(dto);
	}
	
	@Override
	public int inserttodo(TodoDTO insertvalue) {
		return dao.inserttodo(insertvalue);
	}
	
	@Override
	public int updatetodo(int todostate, int todonum) {
		
		int a = 0;
		
		if(todostate==2) {
			a = 0;
		}else {
			a = 2;
		}
		
		return dao.updatetodo(a, todonum);
	}
	
	@Override
	public TodoDTO selecttodoicon(Integer todonum) {
		return dao.selecttodoicon(todonum);
	}
	
	@Override
	public int updatetodoicon(int todostate, int todonum) {
		
		int a = 0;
		
		switch(todostate) {
			case 0:
				a = 1;
				break;
			case 1:
				a = 2;
				break;
			case 2:
				a = 0;
				break;
		}
		
		return dao.updatetodoicon(a, todonum);
	}
	
	@Override
	public int updatetext(TodoDTO dto) {
		return dao.updatetext(dto);
	}
	
	@Override
	public int deletetodo(Integer todonum) {
		return dao.deletetodo(todonum);
	}

}
