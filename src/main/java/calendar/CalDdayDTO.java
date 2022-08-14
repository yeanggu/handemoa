package calendar;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Component("calddayDTO")
public class CalDdayDTO {
	private String memberid;
	private int ddaynum;
	private String date;
}
