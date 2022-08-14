package index;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component("indexdto")
public class IndexDTO {
	int catedetailcode;
	String author;
	String classtitle;
	
	int postreportnum;
	
	int divisioncode;
	int postnum;
	Date postregdate;
	String posttitle;
	int viewcount;
	
	int noticenum;
	String title;
	
	
}
