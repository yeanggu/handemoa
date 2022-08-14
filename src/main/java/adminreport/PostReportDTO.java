package adminreport;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Component("postReportDTO")
@Getter @Setter
public class PostReportDTO {
	int divisioncode;
	int postnum;
	String reportid;
	int postreportnum;
	String reason;

}
