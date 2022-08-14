package dday;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@Component("ddayDTO")
public class DdayDTO {
	private String memberid;
	private int ddaynum;
	private String ddaytitle;
	private String date;
}
