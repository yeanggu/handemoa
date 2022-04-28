package commupost;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommuPostVO {

	int divisioncode;
	int postnum;
	int catedetailcode;
	Date regdate;
	String classtitle;
	String memberid;
	String author;
	String posttitle;
	String content;
	int likecount;
	int dislikecount;
	int viewcount;
	String link;
	String thumbnail;
	String nickname;
	String profileimg;
	int tempsave;
	int history;
	
}
