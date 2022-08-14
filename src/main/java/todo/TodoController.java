package todo;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TodoController {
	
	@Autowired
	@Qualifier("todoservice")
	TodoService service;
	
	//@CrossOrigin(origins = "*")
	@GetMapping("/handemore/todo")
	public List<TodoDTO> todoMain(String memberid, String today) {
		System.out.println(memberid +":"+ today);
		Date date = null ;
		String newdate = null;
		
		try {
		SimpleDateFormat format =new SimpleDateFormat("\"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\"");
		SimpleDateFormat newformat =new SimpleDateFormat("yyyy-MM-dd");
		date = format.parse(today);
		newdate = newformat.format(date);
		System.out.println(newdate);
		} catch(ParseException e) {
            e.printStackTrace();
        }
		
		TodoDTO dto = new TodoDTO();
		dto.setMemberid(memberid);
		dto.setToday(newdate);
		System.out.println("파싱후 : "+ memberid +":"+ date);
		List<TodoDTO> list = service.todoList(dto);
		System.out.println("=======TODOSIZE====="+":"+list.size());
		return list;
	}
	
	@PostMapping("/handemore/insert/todo")
	public int inserttodo(@RequestBody TodoDTO insertvalue) {
		System.out.println("인서트 : " + insertvalue.getMemberid() +":"+insertvalue.getTodotitle()+":"+insertvalue.getToday());
		
		Date date = null ;
		String newdate = null;
		String today = insertvalue.getToday();
		
		try {
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		SimpleDateFormat newformat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = format.parse(today);
		newdate = newformat.format(date);
		System.out.println(newdate);
		} catch(ParseException e) {
            e.printStackTrace();
        }
		
		insertvalue.setToday(newdate);
		int insert = service.inserttodo(insertvalue);
		return insert;
	}
	
	@PutMapping("/handemore/todo/{todonum}")
	public int updatetodo(@PathVariable("todonum") Integer todonum) {
		System.out.println("=======투두넘버up====="+":"+todonum);
		TodoDTO dto = service.selecttodoicon(todonum);
		System.out.println("state값:"+dto.getTodostate());
		
		int updatetodo = service.updatetodo(dto.getTodostate(), todonum);
		return updatetodo;
	}
	
	@PutMapping("/handemore/todo/icon/{todonum}")
	public int updatetodoicon(@PathVariable("todonum") Integer todonum) {
		
		System.out.println("=======투두넘버up2====="+":"+todonum);
		TodoDTO dto = service.selecttodoicon(todonum);
		System.out.println("state값:"+dto.getTodostate());
		System.out.println("num값:"+todonum);
		
		
		int update = service.updatetodoicon(dto.getTodostate(), todonum);
		return update;
	}
	
	@PostMapping("/handemore/updatetext/{todonum}")
	public int updatetext(@PathVariable("todonum") int todonum, @RequestBody TodoDTO updatevalue) {
		
		System.out.println("=======투두넘버up3====="+":"+updatevalue.getTodonum()+":"+updatevalue.getTodotitle());
		TodoDTO dto = new TodoDTO();
		dto.setTodonum(todonum);
		dto.setTodotitle(updatevalue.getTodotitle());
		
		int update = service.updatetext(dto);

		System.out.println("title값:"+updatevalue.getTodotitle());
		System.out.println("num값:"+dto.getTodonum());
		return update;
	}

	@DeleteMapping("/handemore/todo/delete/{todonum}")
	public int deletetodo(@PathVariable("todonum") Integer todonum) {
		System.out.println("=======투두넘버delete====="+":"+todonum);
		int delete = service.deletetodo(todonum);
		
		return delete;
	}
}
