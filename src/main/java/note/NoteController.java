package note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import note.NoteDTO;
import todo.TodoDTO;

@RestController
public class NoteController {
	
	@Autowired
	@Qualifier("noteservice")
	NoteService service;
	
	//@CrossOrigin(origins = "*")
	@GetMapping("/handemore/note")
	public List<NoteDTO> noteMain(String memberid) {
		List<NoteDTO> dto = service.noteList(memberid);
		return dto;
	}

	@PostMapping("/handemore/insert/note")
	public int insertnote(@RequestBody NoteDTO insertvalue) {
		System.out.println("인서트 : " + insertvalue.getMemberid() +":"+insertvalue.getNotetitle());
		int insert = service.insertnote(insertvalue);
		return insert;
	}
	
	@PostMapping("/handemore/updatenote/{notenum}")
	public int updatenote(@PathVariable("notenum") int notenum, @RequestBody NoteDTO updatevalue) {
		
		System.out.println("=======투두넘버up3====="+":"+updatevalue.getNotenum()+":"+updatevalue.getNotetitle());
		NoteDTO dto = new NoteDTO();
		dto.setNotenum(notenum);
		dto.setNotetitle(updatevalue.getNotetitle());
		
		int update = service.updatenote(dto);

		System.out.println("title값:"+updatevalue.getNotetitle());
		System.out.println("num값:"+dto.getNotenum());
		return update;
	}

	@DeleteMapping("/handemore/note/delete/{notenum}")
	public int deletenote(@PathVariable("notenum") int notenum) {
		System.out.println("=======투두넘버delete====="+":"+notenum);
		int delete = service.deletenote(notenum);
		return delete;
	}
	
	
	
	
	
	@GetMapping("/handemore/oxdetail/{notenum}")
	public List<NoteDTO> oxnoteList(@PathVariable("notenum") int notenum,String memberid) {
		System.out.println("=========match========== : "+ memberid+":"+ notenum);
		NoteDTO dto = new NoteDTO();
		dto.setNotenum(notenum);
		dto.setMemberid(memberid);
		List<NoteDTO> list = service.oxnoteList(dto);
		return list;
	}
	
	@PostMapping("/handemore/insert/oxnote/{notenum}")
	public int insertoxnote(@PathVariable("notenum") int notenum, @RequestBody NoteDTO insertvalue) {
		System.out.println("인서트 : " + insertvalue.getMemberid() +":"+insertvalue.getOxtitle());
		NoteDTO dto = new NoteDTO();
		dto.setNotenum(notenum);
		dto.setMemberid(insertvalue.getMemberid());
		dto.setOxtitle(insertvalue.getOxtitle());
		dto.setNotetitle(insertvalue.getNotetitle());
		int insert = service.insertoxnote(dto);
		return insert;
	}
	
	@PutMapping("/handemore/oxdetail/icon/{oxnum}")
	public int updateoxicon(@PathVariable("oxnum") int oxnum) {
		
		System.out.println("=======ox넘버up1====="+":"+oxnum);
		NoteDTO dto = service.selectoxicon(oxnum);
		System.out.println("state값:"+dto.getOxstate());
		System.out.println("num값:"+oxnum);
		
		
		int update = service.updateoxicon(dto.getOxstate(), oxnum);
		return update;
	}

/*	
	@PutMapping("/handemore/detail/oxnote/{oxnum}")
	public int updateoxstate(@PathVariable("oxnum") int oxnum) {
		System.out.println("=======ox넘버up2====="+":"+oxnum);
		NoteDTO dto = service.selectoxicon(oxnum);
		System.out.println("state값:"+dto.getOxstate());
		
		int updatetodo = service.updateoxstate(dto.getOxstate(), oxnum);
		return updatetodo;
	}
*/	
	
	@PostMapping("/handemore/oxupdatetext/{oxnum}")
	public int updateoxtext(@PathVariable("oxnum") int oxnum, @RequestBody NoteDTO updatevalue) {
		
		System.out.println("=======ox넘버up3====="+":"+updatevalue.getOxnum()+":"+updatevalue.getOxtitle());
		NoteDTO dto = new NoteDTO();
		dto.setOxnum(oxnum);
		dto.setOxtitle(updatevalue.getOxtitle());
		
		int update = service.updateoxtext(dto);

		System.out.println("title값:"+updatevalue.getOxtitle());
		System.out.println("num값:"+dto.getOxnum());
		return update;
	}
	
	@DeleteMapping("/handemore/oxdetail/delete/{oxnum}")
	public int deleteoxnote(@PathVariable("oxnum") int oxnum) {
		System.out.println("=======ox넘버delete====="+":"+oxnum);
		int delete = service.deleteoxnote(oxnum);
		return delete;
	}
	
	
	
	
	
	
	@GetMapping("/handemore/studydetail/{notenum}")
	public List<NoteDTO> studynoteList(@PathVariable("notenum") int notenum,String memberid) {
		System.out.println("=========match========== : "+ memberid+":"+ notenum);
		NoteDTO dto = new NoteDTO();
		dto.setNotenum(notenum);
		dto.setMemberid(memberid);
		List<NoteDTO> list = service.studynoteList(dto);
		return list;
	}
	
	@PostMapping("/handemore/insert/studynote/{notenum}")
	public int insertstudynote(@PathVariable("notenum") int notenum, @RequestBody NoteDTO insertvalue) {
		System.out.println("인서트 : " + insertvalue.getMemberid() +":"+insertvalue.getStudytitle());
		NoteDTO dto = new NoteDTO();
		dto.setNotenum(notenum);
		dto.setMemberid(insertvalue.getMemberid());
		dto.setStudytitle(insertvalue.getStudytitle());
		dto.setNotetitle(insertvalue.getNotetitle());
		int insert = service.insertstudynote(dto);
		return insert;
	}
	
	@PutMapping("/handemore/studydetail/icon/{studynum}")
	public int updatestudyicon(@PathVariable("studynum") int studynum) {
		
		System.out.println("=======study넘버up1====="+":"+studynum);
		NoteDTO dto = service.selectstudyicon(studynum);
		System.out.println("state값:"+dto.getStudystate());
		System.out.println("num값:"+studynum);
		
		
		int update = service.updatestudyicon(dto.getStudystate(), studynum);
		return update;
	}

	@PostMapping("/handemore/studyupdatetext/{studynum}")
	public int updatestudytext(@PathVariable("studynum") int studynum, @RequestBody NoteDTO updatevalue) {
		
		System.out.println("=======study넘버up3====="+":"+updatevalue.getStudynum()+":"+updatevalue.getStudytitle());
		NoteDTO dto = new NoteDTO();
		dto.setStudynum(studynum);
		dto.setStudytitle(updatevalue.getStudytitle());
		
		int update = service.updatestudytext(dto);

		System.out.println("title값:"+updatevalue.getStudytitle());
		System.out.println("num값:"+dto.getStudynum());
		return update;
	}
	
	@DeleteMapping("/handemore/studydetail/delete/{studynum}")
	public int deletestudynote(@PathVariable("studynum") int studynum) {
		System.out.println("=======study넘버delete====="+":"+studynum);
		int delete = service.deletestudynote(studynum);
		return delete;
	}

	
	
	
	
	
	
	@GetMapping("/handemore/oxdetailcontent/{oxnum}")
	public List<NoteDTO> oxnotecontentList(@PathVariable("oxnum") int oxnum, String memberid) {
		System.out.println("=========match========== : "+ memberid+":"+ oxnum);
		NoteDTO dto = new NoteDTO();
		
		dto.setOxnum(oxnum);
		dto.setMemberid(memberid);
		List<NoteDTO> list = service.oxnotecontentList(dto);
		return list;
	}
	
	@PostMapping("/handemore/oxupdatetextcontent/{oxnum}")
	public int updateoxtextcontent(@PathVariable("oxnum") int oxnum, @RequestBody NoteDTO updatevalue) {
		
		System.out.println("=======ox넘버up3====="+":"+oxnum+":"+updatevalue.getQuestion() + updatevalue.getAnswer()+":"+ updatevalue.getRegdate());
		NoteDTO dto = new NoteDTO();
		
		Date date = null ;
		String newdate = null;
		String today = updatevalue.getRegdate();
		
		try {
		SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		SimpleDateFormat newformat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = format.parse(today);
		newdate = newformat.format(date);
		System.out.println(newdate);
		} catch(ParseException e) {
            e.printStackTrace();
        }
		
		dto.setRegdate(newdate);
		dto.setOxnum(oxnum);
		dto.setQuestion(updatevalue.getQuestion());
		dto.setAnswer(updatevalue.getAnswer());
		
		int update = service.updateoxtextcontent(dto);

		System.out.println("title값:"+updatevalue.getQuestion());
		System.out.println("num값:"+dto.getOxnum());
		return update;
	}
	
	

	@GetMapping("/handemore/studydetailcontent/{studynum}")
	public List<NoteDTO> studynotecontentList(@PathVariable("studynum") int studynum, String memberid) {
		System.out.println("=========match========== : "+ memberid+":"+ studynum);
		NoteDTO dto = new NoteDTO();
		dto.setStudynum(studynum);
		dto.setMemberid(memberid);
		List<NoteDTO> list = service.studynotecontentList(dto);
		return list;
	}
	
	
	@PostMapping("/handemore/studyupdatetextcontent/{studynum}")
	public int updatestudytextcontent(@PathVariable("studynum") int studynum, @RequestBody NoteDTO updatevalue) {
		
		System.out.println("=======study넘버up3====="+":"+studynum+":"+updatevalue.getContent());
		NoteDTO dto = new NoteDTO();
		dto.setStudynum(studynum);
		dto.setContent(updatevalue.getContent());
		
		int update = service.updatestudytextcontent(dto);

		System.out.println("content값:"+updatevalue.getContent());
		System.out.println("num값:"+dto.getStudynum());
		return update;
	}

}
