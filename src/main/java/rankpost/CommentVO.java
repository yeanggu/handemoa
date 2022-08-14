package rankpost;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentVO {
	
	int divisioncode;
	int postnum;
	String memberid;
	int commentnum;
	Date regdate;
	String commentcontent;
	int likecount;
	String nickname;
}
