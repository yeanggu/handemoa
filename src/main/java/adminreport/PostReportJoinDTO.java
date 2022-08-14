package adminreport;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component("postReportJoinDTO")
@Getter @Setter
public class PostReportJoinDTO {
	int divisioncode;
	String divisionname;
	String nickname;
	int postnum;
	String posttitle;
	int count;	
}
