package profile;

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

import profile.SearchDTO;


@Controller("ProfileController")
public class ProfileController {

	@Autowired
	@Qualifier("profileservice")
	ProfileService service;

	
		//게시물 리스트
		@RequestMapping(value = "/profile", method = RequestMethod.GET)
	    public ModelAndView AllListView(
	            @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
	            @RequestParam(value = "cntPerpage", required = false, defaultValue = "10") int cntPerpage,
	            @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
	            @RequestParam(required = false, defaultValue = "") String searchType,
				@RequestParam(required = false, defaultValue = "") String keyword,
	            @RequestParam(required = false, defaultValue = "ranking_select") String postType,
	            @RequestParam(required = false) String nickname
	            ) throws Exception {
				
				ModelAndView mv = new ModelAndView();
				SearchDTO search = new SearchDTO();
				search.setSearchType(searchType);
				search.setKeyword(keyword);
				search.setCurrentPage(currentPage);
				search.setCntPerPage(cntPerpage);
				search.setPageSize(pageSize);
				search.setPostType(postType);
				search.setNickname(nickname);
				search.setSearchTypeKeyword(searchType, keyword);
		        
				int postlistCnt = service.searchcount(search);//공지 db 총갯수 저장
		        search.setTotalRecordCount(postlistCnt);//총개수로 cal계산
		        System.out.println("===>컨트롤러 /postlistCnt ==> "+postlistCnt);
		        
				int commentlistCnt = service.commentcount(search);//공지 db 총갯수 저장
		        System.out.println("===>컨트롤러 /commentlistCnt ==> "+commentlistCnt);
		        
		        System.out.println("===>컨트롤러 /searchType ==> "+searchType);
		        
		        
		        mv.addObject("pagedto",search);
		        mv.addObject("memberlist",service.memberintroduce(nickname));
		        mv.addObject("profilelist",service.searchlist(search));
		        mv.addObject("commentlist",service.commentlist(search));
		        mv.setViewName("profile");
		        return mv;	    		
	    		
		}
		
}
