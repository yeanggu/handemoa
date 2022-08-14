package dday;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/handemore/dday")
public class DdayController {
	
	@Autowired
	@Qualifier("ddayservice")
	DdayService service;
	
	@GetMapping("/{memberid}")
	public List<DdayDTO> getList(@PathVariable("memberid")String memberid) {
		List<DdayDTO> dto = service.ddayList(memberid);
		return dto;
	}
	
	@PostMapping("/create")
	public int createDday(@RequestBody DdayDTO dto) {
		int result = service.insertDday(dto);
		return result;
	}
	
	@PutMapping
	public int updateDday(@RequestBody DdayDTO dto) {
		int result = service.updateDday(dto);
		return result;
	}
	
	//삭제
	@GetMapping("/delete")
	public int deleteDday(@RequestParam(value="ddaynums") List<Integer> ddaynums) {
		int result = service.deleteDday(ddaynums);
		return result;
	}
}
