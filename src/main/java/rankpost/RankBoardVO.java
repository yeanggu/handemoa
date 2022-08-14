package rankpost;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankBoardVO {

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
