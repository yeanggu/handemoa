package todo;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Component("tododto")
public class TodoDTO {
	String memberid;
	String todotitle;
	int todonum;
	String today;
	Date regdate;
	int todostate;
}
