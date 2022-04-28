package member;

import org.springframework.context.annotation.Primary;


@Primary
public interface MemberService {

	//회원가입
	public int memberinsert(MemberDTO dto);
	
	//중복조회
	public int checkmember(String id);
	public int checkmember_nickname(String nickname);
	public int checkmember_email(String email);
	public int checkmember_phone(String phone);
	

	public MemberDTO login(MemberDTO memberdto);
	
	public int memberquit(String status);
	
	public int memberedit(MemberDTO dto);
	

	
	
	 

}


