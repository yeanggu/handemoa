package member;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("memberDAO")
public interface MemberDAO {



	public int memberinsert(MemberDTO dto);

	
	public int checkmember(String id);
	/*
	 * { MemberDTO dto = session.selectOne("member", id); return dto; }
	 */
	public int checkmember_nickname(String nickname);
	
	public int checkmember_email(String email);
	
	public int checkmember_phone(String phone);

	
	
	public MemberDTO login(MemberDTO memberdto);
	
	public int memberquit(String status);
	
	public int memberedit(MemberDTO dto);
	

}
