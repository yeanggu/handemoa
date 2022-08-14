package adminmember;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller("AdminMemberController")
public class AdminMemberController {

	@Autowired
	@Qualifier("adminmemberservice")
	AdminMemberService service;

	
		//회원리스트
		@RequestMapping(value = "/adminmember", method = RequestMethod.GET)
	    public ModelAndView AllListView(
            @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
            @RequestParam(value = "cntPerpage", required = false, defaultValue = "10") int cntPerpage,
            @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
            @RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false, defaultValue = "") String keyword,
            @RequestParam(required = false, defaultValue = "all_select") String quitType) throws Exception {
			
			ModelAndView mv = new ModelAndView();
			SearchDTO search = new SearchDTO();
			search.setSearchType(searchType);
			search.setKeyword(keyword);
			search.setCurrentPage(currentPage);
			search.setCntPerPage(cntPerpage);
			search.setPageSize(pageSize);
			search.setQuitType(quitType);
			search.setSearchTypeKeyword(searchType, keyword);
	        int listCnt = service.searchcount(search);//공지 db 총갯수 저장
	        search.setTotalRecordCount(listCnt);//총개수로 cal계산
	        System.out.println("===>컨트롤러 /alllistview ==> "+listCnt);
	        
	        mv.addObject("pagedto",search);
	        mv.addObject("adminmemberlist",service.searchlist(search));
	        mv.setViewName("adminmember");
	        return mv;
		}
		
		
		// 회원 선택 삭제
		@ResponseBody
		@RequestMapping(value = "/memberdeleteselect", method = RequestMethod.POST)
		public String deleteselect(HttpServletRequest request) {
		 
			String[] ajaxMsg = request.getParameterValues("valueArr");
			for(int i=0;i<ajaxMsg.length;i++) {
				service.deleteselect(ajaxMsg[i]);
			}
			return "redirect: adminmember";
		}
		
		//회원 선택 탍퇴
		@ResponseBody
		@RequestMapping(value = "/memberquitselect", method = RequestMethod.POST)
		public String quitselect(HttpServletRequest request) {
				
			String[] ajaxMsg = request.getParameterValues("valueArr");
			for(int i=0;i<ajaxMsg.length;i++) {
				service.updatepost(ajaxMsg[i]);
				service.updatecomment(ajaxMsg[i]);
				service.updatecommentreport(ajaxMsg[i]);
				service.updatepostreport(ajaxMsg[i]);
				service.quitselect(ajaxMsg[i]);
			}
			return "redirect: adminmember";
		}
		
		//회원 탈퇴 여부 
		@ResponseBody
		@RequestMapping(value = "/quitOX", method = RequestMethod.POST)
		public List<String> selectquit(@RequestBody Map<String,List<String>> valueArr,Model model) throws Exception {
			System.out.println("=======================================" + valueArr);
			List<String> quitOX= service.quitmemberOX(valueArr);
			System.out.println("=======================================" + quitOX);
	        model.addAttribute("quitOX", quitOX);    
			return quitOX;
		}
		
		
		//json객체 : Map<String,List<String>
				//json배열 : List<T>
				//String : String
				//NUmber : Double

/*		
		//관리자 회원 검색
		@RequestMapping(value ="/adminsearchmember", method = RequestMethod.GET)
		public ModelAndView adminsearchnotice (
				@RequestParam(required = false, defaultValue = "1") int currentPage, 
				@RequestParam(required = false, defaultValue = "10") int cntPerpage,
				@RequestParam(required = false, defaultValue = "5") int pageSize,
				@RequestParam(required = false) String searchType,
				@RequestParam(required = false) String keyword) {
			ModelAndView mv =new ModelAndView();
			SearchDTO search = new SearchDTO();
			search.setSearchType(searchType);
			search.setKeyword(keyword);
			search.setCurrentPage(currentPage);
			search.setCntPerPage(cntPerpage);
			search.setPageSize(pageSize);
			//전체 게시글 수
			int listCnt = service.searchcount(search);
			search.setTotalRecordCount(listCnt);
			System.out.println("===>컨트롤러 /adminsearchnotice ==> "+listCnt);
			
			mv.addObject("adminpagedto", search);
			mv.addObject("adminmemberlist", service.searchlist(search));
			mv.setViewName("adminmember");
			return mv;
		}
*/	
}
