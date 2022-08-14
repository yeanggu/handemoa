package rankpost;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlarmDTO {
	
	int alarmnum;
	String id; //알람 수신자(글 작성자)
	String caller; //알람 발신자(댓글작성자)
	int postnum; //글 번호
	String alarmcontent; // 댓글 내용
	Date time_al; // 작성시간(알람시간)
	boolean read_al; // 읽음 유무
		

}
