package admin;

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
@Component("admindto")
public class AdminDTO {
	String id;
	String nickname;
	String phone;
	Date birth;
	Date regdate;
	int status;
	
	int postreportnum;
	
	int divisioncode;
	int postnum;
	Date postregdate;
	String posttitle;
	int viewcount;
	
	int noticenum;
	String title;
	
	
}
