package calendar;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Component("caltodoDTO")
public class CalTodoDTO {
	private String memberid;
	private int todonum;
	private String regdate;
}
