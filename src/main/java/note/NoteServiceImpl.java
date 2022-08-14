package note;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import todo.TodoDTO;

@Service("noteservice")
public class NoteServiceImpl implements NoteService {

	@Autowired
	@Qualifier("noteDAO")
	NoteDAO dao;
	
	@Override
	public List<NoteDTO> noteList(String memberid) {
		return dao.noteList(memberid);
	}
	
	@Override
	public int insertnote(NoteDTO insertvalue) {
		return dao.insertnote(insertvalue);
	}
	
	@Override
	public int updatenote(NoteDTO dto) {
		return dao.updatenote(dto);
	}
	
	@Override
	public int deletenote(int notenum) {
		return dao.deletenote(notenum);
	}

	
	
	
	@Override
	public List<NoteDTO> oxnoteList(NoteDTO dto) {
		return dao.oxnoteList(dto);
	}
	
	@Override
	public int insertoxnote(NoteDTO dto) {
		return dao.insertoxnote(dto);
	}
	
	@Override
	public NoteDTO selectoxicon(int oxnum) {
		return dao.selectoxicon(oxnum);
	}
	
	@Override
	public int updateoxicon(int oxstate, int oxnum) {
		
		int a = 0;
		
		switch(oxstate) {
			case 0:
				a = 1;
				break;
			case 1:
				a = 2;
				break;
			case 2:
				a = 3;
				break;
			case 3:
				a = 0;
				break;
		}
		
		return dao.updateoxicon(a, oxnum);
	}

/*	
	@Override
	public int updateoxstate(int oxstate, int oxnum) {
		int a = 0;
		
		if(oxstate==3) {
			a = 0;
		}else {
			a = 3;
		}
		
		return dao.updateoxstate(a, oxnum);
	}
*/
	
	@Override
	public int updateoxtext(NoteDTO dto) {
		return dao.updateoxtext(dto);
	}
	
	@Override
	public int deleteoxnote(int oxnum) {
		return dao.deleteoxnote(oxnum);
	}
	
	
	
	
	
	
	
	@Override
	public List<NoteDTO> studynoteList(NoteDTO dto) {
		return dao.studynoteList(dto);
	}
	
	@Override
	public int insertstudynote(NoteDTO dto) {
		return dao.insertstudynote(dto);
	}
	
	@Override
	public NoteDTO selectstudyicon(int studynum) {
		return dao.selectstudyicon(studynum);
	}
	
	@Override
	public int updatestudyicon(int studystate, int studynum) {
		
		int a = 0;
		
		switch(studystate) {
			case 0:
				a = 1;
				break;
			case 1:
				a = 2;
				break;
			case 2:
				a = 3;
				break;
			case 3:
				a = 0;
				break;
		}
		
		return dao.updatestudyicon(a, studynum);
	}
	
	@Override
	public int updatestudytext(NoteDTO dto) {
		return dao.updatestudytext(dto);
	}
	
	@Override
	public int deletestudynote(int studynum) {
		return dao.deletestudynote(studynum);
	}

	
	
	
	
	@Override
	public List<NoteDTO> oxnotecontentList(NoteDTO dto) {
		return dao.oxnotecontentList(dto);
	}

	@Override
	public int updateoxtextcontent(NoteDTO dto) {
		return dao.updateoxtextcontent(dto);
	}

	@Override
	public List<NoteDTO> studynotecontentList(NoteDTO dto) {
		return dao.studynotecontentList(dto);
	}

	@Override
	public int updatestudytextcontent(NoteDTO dto) {
		return dao.updatestudytextcontent(dto);
	}
	
	
	
	
	
	
}
