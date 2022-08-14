package rankpost;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankPostDTO {

	int divisioncode;
	int postnum;
	int catedetailcode;
	Date regdate;
	String author;
	String classtitle;
	String memberid;
	String posttitle;
	String content;
	int likecount;
	int dislikecount;
	int viewcount;
	String link;
	String thumbnail;
	int tempsave;
	int history;
	String nickname;
	String profileimg;
	
}
