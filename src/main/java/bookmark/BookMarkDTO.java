package bookmark;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component("bookmarkDTO")
@Getter @Setter
public class BookMarkDTO {
	private int bookmarknum;
	private String memberid;
	private int postnum;
}
