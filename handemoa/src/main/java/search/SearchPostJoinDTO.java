package search;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component("searchPostJoinDTO")
@Setter @Getter
public class SearchPostJoinDTO {
	
	int divisioncode;
	int postnum;
	String catename;
	int catedetailcode;
	String posttitle;
	String regdate;
	String author;
	String classtitle;
	int likecount;
	int dislikecount;
	int viewcount;
	String thumbnail;
	String nickname;
}
