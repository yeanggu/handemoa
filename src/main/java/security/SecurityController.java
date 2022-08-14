package security;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import member.MemberDTO;

@RestController
@RequestMapping("/handemore")
public class SecurityController {

	@Autowired
	private SecurityService securityservice;
	String token;
	
	@GetMapping("/create/token")
	@ResponseBody
	public Map<String, Object> createToken(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		if (session.getAttribute("member") == null) {
			map.put("result", "notoken");
			return map;
		}
		MemberDTO memberdto = (MemberDTO) session.getAttribute("member");
		System.out.println("토큰id :"+memberdto.getId());
		
		
		System.out.println("토큰생성 시작");
		System.out.println("받은 subject: " + memberdto.getId());
		token = securityservice.createToken(memberdto.getId(), (30*1000*60));
		
		map.put("result", token);
		System.out.println(token);
		return map;
	}
	
	@GetMapping("/get/subject")
	@ResponseBody
	public String getSubject() {
				
		if(token == null) {
			return "notoken";
		}
		
		String subject = securityservice.getSubject(token);
		
		System.out.println("토큰 해석한 값:"+subject);
		return subject;
	}
}
