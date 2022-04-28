package commupost;


import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommuCommentVO {
	
	int divisioncode;
	int postnum;
	String memberid;
	int commentnum;
	String regdate;
	String commentcontent;
	int likecount;
	String nickname;
}
