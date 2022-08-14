package bookmark;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component("bookMarkJoinDTO")
@Getter @Setter
public class BookMarkJoinDTO {
	
	private int bookmarknum;
	private String memberid;
	private int postnum;
	private String posttitle;
	private String regdate;
	private String nickname;
	private String classtitle;
	private String author;
	private String thumbnail;

}
