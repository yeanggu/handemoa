package adminmember;

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
@Component("adminmemberdto")
public class AdminMemberDTO {
	String id;
	String nickname;
	String password;
	String email;
	String phone;
	Date birth;
	Date regdate;
	int status;
	Date quitdate;
	String profileimg;
	String intro;
}
