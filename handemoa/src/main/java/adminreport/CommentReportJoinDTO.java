package adminreport;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component("commentReportJoinDTO")
@Getter @Setter
public class CommentReportJoinDTO {
	int divisioncode;
	String divisionname;
	String nickname;
	int commentnum;
	String commentcontent;
	int count;	
}
