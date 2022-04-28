package postmanage;

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

import notice.NoticeDTO;
import postmanage.SearchDTO;


@Controller("PostManageController")
public class PostManageController {

	@Autowired
	@Qualifier("postmanageservice")
	PostManageService service;

	
		//게시물 리스트
		@RequestMapping(value = "/postmanage", method = RequestMethod.GET)
	    public ModelAndView AllListView(
	            @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
	            @RequestParam(value = "cntPerpage", required = false, defaultValue = "10") int cntPerpage,
	            @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
	            @RequestParam(required = false, defaultValue = "") String searchType,
				@RequestParam(required = false, defaultValue = "") String keyword,
	            @RequestParam(required = false) String postType) throws Exception {
				
				ModelAndView mv = new ModelAndView();
				SearchDTO search = new SearchDTO();
				search.setSearchType(searchType);
				search.setKeyword(keyword);
				search.setCurrentPage(currentPage);
				search.setCntPerPage(cntPerpage);
				search.setPageSize(pageSize);
				search.setPostType(postType);
				search.setSearchTypeKeyword(searchType, keyword);
		        int listCnt = service.searchcount(search);//공지 db 총갯수 저장
		        search.setTotalRecordCount(listCnt);//총개수로 cal계산
		        System.out.println("===>컨트롤러 /alllistview ==> "+listCnt);
		        System.out.println("===>컨트롤러 /searchType ==> "+searchType);
		        mv.addObject("pagedto",search);
		        mv.addObject("adminpostlist",service.searchlist(search));
		        mv.setViewName("postmanage");
		        return mv;	    		
	    		
		}
		
		
		//게시물 히스토리 이동
		@ResponseBody
		@RequestMapping(value = "/posthistoryselect", method = RequestMethod.POST)
		public int quitselect(@RequestParam(value="valueArr[]") List<Integer> valueArr)  {
			System.out.println("=======================================" + valueArr.size());
			int cnt = 0;
			for(int i = 0; i < valueArr.size(); i++ ) {
				int one = service.quitselect(valueArr.get(i));
				cnt = cnt + one;
			}
			return cnt;
		}
		
		
		////////////////////////////////히스토리//////////////////////////////////
		
		//회원리스트
		@RequestMapping(value = "/history", method = RequestMethod.GET)
	    public ModelAndView AllListView2(
	            @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
	            @RequestParam(value = "cntPerpage", required = false, defaultValue = "10") int cntPerpage,
	            @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
	            @RequestParam(required = false, defaultValue = "title") String searchType,
				@RequestParam(required = false, defaultValue = "") String keyword,
	            @RequestParam(required = false) String postType) throws Exception {
				
				ModelAndView mv = new ModelAndView();
				SearchDTO search = new SearchDTO();
				search.setSearchType(searchType);
				search.setKeyword(keyword);
				search.setCurrentPage(currentPage);
				search.setCntPerPage(cntPerpage);
				search.setPageSize(pageSize);
				search.setPostType(postType);
				search.setSearchTypeKeyword(searchType, keyword);
		        int listCnt = service.searchcount2(search);//공지 db 총갯수 저장
		        search.setTotalRecordCount(listCnt);//총개수로 cal계산
		        System.out.println("===>컨트롤러 /alllistview ==> "+listCnt);
		        
		        mv.addObject("pagedto",search);
		        mv.addObject("adminpostlist",service.searchlist2(search));
		        mv.setViewName("history");
		        return mv;	    		
		}
		
		
		
		//게시물 히스토리 이동
		@ResponseBody
		@RequestMapping(value = "/restorepost", method = RequestMethod.POST)
		public int quitselect2(@RequestParam(value="valueArr[]") List<Integer> valueArr)  {
			System.out.println("=======================================" + valueArr.size());
			int cnt = 0;
			for(int i = 0; i < valueArr.size(); i++ ) {
				int one = service.restorepost(valueArr.get(i));
				cnt = cnt + one;
			}
			return cnt;
		}		
		
		
		// 회원 선택 삭제
		@ResponseBody
		@RequestMapping(value = "/deletepost", method = RequestMethod.POST)
		public int deleteselect(@RequestParam(value="valueArr[]") List<Integer> valueArr) {
			System.out.println("=======================================" + valueArr.size());
			int cnt = 0;
			for(int i = 0; i < valueArr.size(); i++ ) {
				int one = service.deleteselect(valueArr.get(i));
				cnt = cnt + one;
			}
			return cnt;
		}
		
		
		
	/*	

	  	//사용자 공지 조회
		@RequestMapping("/postnum")
		public ModelAndView postinner(int postnum) {
			ModelAndView mv = new ModelAndView();
			PostManageDTO dto = service.postinner(postnum);
			mv.addObject("postdto",dto);
			mv.setViewName("postmanageinner");
			return mv;
		}
	  
	  
		//사용자 공지 조회
		@RequestMapping("/historynum")
		public ModelAndView postinner2(int postnum) {
			ModelAndView mv = new ModelAndView();
			PostManageDTO dto = service.postinner(postnum);
			mv.addObject("postdto",dto);
			mv.setViewName("historyinner");
			return mv;
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
		
		
		//관리자 회원 검색
		@RequestMapping(value ="/postmanagesearch", method = RequestMethod.GET)
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
			
			mv.addObject("postmanagepagedto", search);
			mv.addObject("adminpostlist", service.searchlist(search));
			mv.setViewName("postmanage");
			return mv;
		}		
		
		//관리자 회원 검색
		@RequestMapping(value ="/historysearch", method = RequestMethod.GET)
		public ModelAndView adminsearchnotice2 (
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
			int listCnt = service.searchcount2(search);
			search.setTotalRecordCount(listCnt);
			System.out.println("===>컨트롤러 /adminsearchnotice ==> "+listCnt);
			
			mv.addObject("postmanagepagedto", search);
			mv.addObject("adminpostlist", service.searchlist2(search));
			mv.setViewName("history");
			return mv;
		}		
		
		
		
*/
		
		
		//json객체 : Map<String,List<String>
				//json배열 : List<T>
				//String : String
				//NUmber : Double
		
	
}
