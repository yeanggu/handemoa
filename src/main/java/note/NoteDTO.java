package note;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Component("notedto")
public class NoteDTO {
	private String memberid;
	private String notetitle;
	private int notenum;
	
	
	String oxtitle;
	int oxnum;
	String regdate;
	String question;
	String answer;
	int oxstate;
	
	
	String studytitle;
	int studynum;
	String content;
	int studystate;
	
	
}
