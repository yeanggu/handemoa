package notice;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller("NoticeController")
public class NoticeController {

	@Autowired
	@Qualifier("noticeservice")
	NoticeService service;
	
	
	//사용자 공지리스트
	@RequestMapping(value = "/notice", method = RequestMethod.GET)
    public ModelAndView AllListView(
            @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
            @RequestParam(value = "cntPerpage", required = false, defaultValue = "7") int cntPerpage,
            @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
            @RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false, defaultValue = "") String keyword
            ) throws Exception {
   
		ModelAndView mv = new ModelAndView();
		SearchDTO search = new SearchDTO();
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		search.setCurrentPage(currentPage);
		search.setCntPerPage(cntPerpage);
		search.setPageSize(pageSize);
		// 검색 타입과 검색어
		search.setSearchTypeKeyword(searchType, keyword);
		//전체 게시글 수
		int listCnt = service.searchcount(search);
		search.setTotalRecordCount(listCnt);
		System.out.println("===>컨트 롤러 /searchnotice ==> "+listCnt);
		
		
		mv.addObject("pagedto", search);
		mv.addObject("Alllist", service.searchlist(search));
		mv.setViewName("notice");
        return mv;
    }
	
	//관리자 공지 리스트
	@RequestMapping(value = "/adminnotice", method = RequestMethod.GET)
	public ModelAndView adminselectnotice(
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "cntPerpage", required = false, defaultValue = "7") int cntPerpage,
            @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
            @RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false, defaultValue = "") String keyword
            ) throws Exception {
   
		ModelAndView mv = new ModelAndView();
		SearchDTO search = new SearchDTO();
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		search.setCurrentPage(currentPage);
		search.setCntPerPage(cntPerpage);
		search.setPageSize(pageSize);
		// 검색 타입과 검색어
		search.setSearchTypeKeyword(searchType, keyword);
		//전체 게시글 수
		int listCnt = service.searchcount(search);
		search.setTotalRecordCount(listCnt);
		System.out.println("===>컨트 롤러 /searchnotice ==> "+listCnt);
 
        mv.addObject("pagedto",search);
        mv.addObject("Alllist",service.sellectalllist(search));
        mv.setViewName("adminnotice");
		return mv;
	}
	

	//사용자 공지 조회
	@RequestMapping("/noticenum")
	public ModelAndView noticeinner(int noticenum) {
		ModelAndView mv = new ModelAndView();
		NoticeDTO dto = service.noticeinner(noticenum);
		mv.addObject("noticedto",dto);
		mv.setViewName("noticeinner");
		return mv;
	}
	
	//관리자 공지 조회
	@RequestMapping("/adminnoticenum")
	public ModelAndView adminnoticeinner(int noticenum) {
		ModelAndView mv = new ModelAndView();
		NoticeDTO dto = service.noticeinner(noticenum);
		mv.addObject("noticedto",dto);
		mv.setViewName("adminnoticeinner");
		return mv;
	}
	
	//공지 등록 폼
	@RequestMapping("/insertnotice")
	public String insert() {
		return "insertnotice";
	}
	
	//공지 전송
	@RequestMapping("/adminnotice")
	public String insertnotice(NoticeDTO dto) {
		int insertrow = service.insertnotice(dto);
		if(insertrow>0) {
			return "redirect:adminnotice";
		}
		return "redirect:insertnotice";
	}
	
	//공지 삭제
	@RequestMapping("/deletenotice")
	public String deletenotice(int noticenum) {
		int deleterow = service.deletenotice(noticenum);
		if(deleterow >0) { 
			return "redirect:adminnotice";
		}
		return "redirect:/adminnoticeinner?noticenum="+noticenum; 
	}
	
	//공지 수정 view
	@RequestMapping("/updateview")
	public ModelAndView updateview(int noticenum) {
		ModelAndView mv = new ModelAndView();
		NoticeDTO dto = service.updateview(noticenum);
		mv.addObject("noticedto",dto);
		mv.setViewName("updatenotice");
		return mv;
	}
	
	//공지 수정
	@RequestMapping("/updatenotice")
	public String updatenotice(NoticeDTO dto) {
		int insertrow = service.updatenotice(dto);
		if(insertrow>0) {
			return "redirect:adminnotice";
		}
		return "redirect:updateview?noticenum="+dto.noticenum;
	}
	
	
	// 공지 선택 삭제
	@ResponseBody
	@RequestMapping(value = "/deleteselect", method = RequestMethod.POST)
	public String deleteselect(HttpServletRequest request) {
	 
		String[] ajaxMsg = request.getParameterValues("valueArr");
		for(int i=0;i<ajaxMsg.length;i++) {
			service.deleteselect(ajaxMsg[i]);
		}
		return "redirect: adminnotice";
	}
	
/*	
	
	//관리자 공지 검색
	@RequestMapping(value ="/adminsearchnotice", method = RequestMethod.GET)
	public ModelAndView adminsearchnotice (
			@RequestParam(required = false, defaultValue = "1") int currentPage, 
			@RequestParam(required = false, defaultValue = "7") int cntPerpage,
			@RequestParam(required = false, defaultValue = "5") int pageSize,
			@RequestParam(required = false, defaultValue = "title") String searchType,
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
		
		
		mv.addObject("pagedto", search);
		mv.addObject("Alllist", service.searchlist(search));
		mv.setViewName("adminnotice");
		return mv;
	}
		
 
	//사용자 공지 검색
	@RequestMapping(value ="/searchnotice", method = RequestMethod.GET)
	public ModelAndView searchnotice (
			@RequestParam(required = false, defaultValue = "1") int currentPage, 
			@RequestParam(required = false, defaultValue = "7") int cntPerpage,
			@RequestParam(required = false, defaultValue = "5") int pageSize,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String keyword) {
		ModelAndView mv =new ModelAndView();
		SearchDTO search = new SearchDTO();
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		search.setCurrentPage(currentPage);
		search.setCntPerPage(cntPerpage);
		search.setPageSize(pageSize);
		// 검색 타입과 검색어
		search.setSearchTypeKeyword(searchType, keyword);
		//전체 게시글 수
		int listCnt = service.searchcount(search);
		search.setTotalRecordCount(listCnt);
		System.out.println("===>컨트 롤러 /searchnotice ==> "+listCnt);
		
		
		mv.addObject("pagedto", search);
		mv.addObject("Alllist", service.searchlist(search));
		mv.setViewName("notice");
		return mv;
	}

	//공지 리스트
//	@RequestMapping(value = "/notice", method = RequestMethod.GET)
//	public ModelAndView selectnotice() {
//		ModelAndView mv = new ModelAndView();
//		List<NoticeDTO> list = service.noticelist();
//		mv.addObject("noticelist", list);
//		mv.setViewName("notice");
//		return mv;
//	}

//	//관리자 공지 리스트
//	@RequestMapping(value = "/adminnotice", method = RequestMethod.GET)
//	public ModelAndView adminselectnotice() {
//		ModelAndView mv = new ModelAndView();
//		List<NoticeDTO> list = service.noticelist();
//		mv.addObject("noticelist", list);
//		mv.setViewName("adminnotice");
//		return mv;
//	}

	
 * 	@RequestMapping("/membermybatissearch")
	public ModelAndView memberlist(String[] address) {
		ModelAndView mv =new ModelAndView();
		List<String> list = service.memberlist(address);
		mv.addObject("memberaddresslist",list);
		mv.addObject("name",list);
		mv.setViewName("notice");
		return mv;
	}
	
	//공지 수정
	@RequestMapping("/updatenotice")
	public ModelAndView updatenotice(NoticeDTO dto) {
		ModelAndView mv = new ModelAndView();
		int updaterow = service.updatenotice(dto);
		mv.setViewName("redirect:adminnotice");
		return mv;
	}
	
		
	@RequestMapping(value = "/noticeinsert", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
	public ModelAndView insertnotice(int noticenum, String title, Date regdate, String content) {
		ModelAndView mv = new ModelAndView();
		NoticeDTO dto = new NoticeDTO();
		int insertRow = service.insertnotice(dto);
		mv.addObject("insertRow", insertRow);
		mv.setViewName("noticeinsert");
		return mv;
	}
*/	
	
	
	
}
