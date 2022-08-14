package note;

import java.util.List;

import todo.TodoDTO;

public interface NoteService {

		public List<NoteDTO> noteList(String memberid);
		
		public int insertnote(NoteDTO insertvalue);
		
		public int updatenote(NoteDTO dto);
		
		public int deletenote(int notenum);
		
		
		
		public List<NoteDTO> oxnoteList(NoteDTO dto);
		
		public int insertoxnote(NoteDTO dto);

		public NoteDTO selectoxicon(int oxnum);

		public int updateoxicon(int oxstate, int oxnum);

		//public int updateoxstate(int oxstate, int oxnum);			
		
		public int updateoxtext(NoteDTO dto);
		
		public int deleteoxnote(int oxnum);
		
		
		
		public List<NoteDTO> studynoteList(NoteDTO dto);
		
		public int insertstudynote(NoteDTO dto);

		public NoteDTO selectstudyicon(int studynum);

		public int updatestudyicon(int studystate, int studynum);

		public int updatestudytext(NoteDTO dto);
		
		public int deletestudynote(int studynum);
		
		
		
		public List<NoteDTO> oxnotecontentList(NoteDTO dto);
		
		public int updateoxtextcontent(NoteDTO dto);

		public List<NoteDTO> studynotecontentList(NoteDTO dto);
		
		public int updatestudytextcontent(NoteDTO dto);
}
