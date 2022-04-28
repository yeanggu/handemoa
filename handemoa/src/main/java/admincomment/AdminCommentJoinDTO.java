package admincomment;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component("admincommentjoindto")
@Getter @Setter
public class AdminCommentJoinDTO {

	int divisioncode;
	String divisionname;
	int commentnum;
	String regdate;
	String nickname;
	String commentcontent;
	int postnum;
}
