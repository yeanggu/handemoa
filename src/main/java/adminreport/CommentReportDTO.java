package adminreport;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Component("commentReportDTO")
public class CommentReportDTO {
	int divisioncode;
	int commentnum;
	String reportid;
	int commentreportnum;
	String reason;

}
