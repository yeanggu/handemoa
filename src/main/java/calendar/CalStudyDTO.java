package calendar;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Component("calstudyDTO")
public class CalStudyDTO {
	private String memberid;
	private int studynum;
	private String regdate;
}
