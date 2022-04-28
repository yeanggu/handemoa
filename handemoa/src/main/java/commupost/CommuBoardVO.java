package commupost;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommuBoardVO {

	int rank;
	int score;
	int divisioncode;
	int postnum;
	String author;
	String classtitle;
	String posttitle;
	int likecount;
	int dislikecount;
}
