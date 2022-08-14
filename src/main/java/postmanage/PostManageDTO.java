package postmanage;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component("postmanagedto")
public class PostManageDTO {
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
}
