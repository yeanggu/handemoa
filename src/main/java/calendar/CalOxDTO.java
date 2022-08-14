package calendar;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Component("caloxDTO")
public class CalOxDTO {
	private String memberid;
	private int oxnum;
	private String regdate;
}
