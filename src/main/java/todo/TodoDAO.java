package todo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("todoDAO")
public interface TodoDAO {
	
	//todo 목록 조회
	public List<TodoDTO> todoList(TodoDTO dto);
	
	//todo 목록 조회
	public int inserttodo(TodoDTO insertvalue);
	
	//todo 목록 조회
	public int updatetodo(int todostate, int todonum);
	
	//todo 목록 조회
	public TodoDTO selecttodoicon(int todonum);

	public int updatetodoicon(int todostate, int todonum);
	
	public int updatetext(TodoDTO dto);
	
	public int deletetodo(Integer todonum);
}
