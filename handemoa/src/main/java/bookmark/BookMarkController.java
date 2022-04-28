package bookmark;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import member.MemberDTO;

@Controller
public class BookMarkController {
	
	@Autowired
	@Qualifier("bookmarkservice")
	BookMarkService service;
	
	//페이지 출력 행, 페이지 수 고정값
	private int limitRows = 6;
	private int limitPage = 5;
	
	@RequestMapping("/bookmark")
	public String bookmark(HttpServletRequest request) {
		HttpSession session = request.getSession();

		//로그인 여부 체크
		if (session.getAttribute("member") == null) {
			return "redirect:/login";
		}
		else {
		return "redirect:/bookmarkview?currentpage=1&searchtxt=";
		}
	}
	
	@RequestMapping("/bookmarkview")
	public ModelAndView bookmark(HttpServletRequest request, int currentpage, String searchtxt) {
		HttpSession session = request.getSession();
		MemberDTO logindto = (MemberDTO) session.getAttribute("member");
		
		//페이징
		int pageStartRow = limitRows * ( currentpage - 1);
		
		Map<String, Object> searchdetail = new HashMap<String, Object>();
		searchdetail.put("memberid", logindto.getId());
		searchdetail.put("searchtxt", searchtxt);
		searchdetail.put("pagestartrow", pageStartRow);
		searchdetail.put("limitrows", limitRows);

		//북마크 조회 기본, 제목 기준 검색
		List<BookMarkJoinDTO> bookMarkList = service.bookMarkList(searchdetail);
		int bookMarkRows = service.bookMarkRows(searchdetail);					
		ViewPageDTO pagedto = new ViewPageDTO(limitRows, limitPage, currentpage, bookMarkRows);

		ModelAndView mv = new ModelAndView();		
		mv.addObject("searchdetail", searchdetail);
		mv.addObject("pagedto", pagedto);
		mv.addObject("bookMarkList", bookMarkList);
		
		mv.setViewName("bookmark/bookmarkview");
		return mv;
	}

	@PostMapping("/bookmarkinsert")
	@ResponseBody
	public int bookmarkinsert(BookMarkDTO dto) {
		int result = 0;
		//북마크에 등록하기
		//해당 북마크 있는지 한번 더 체크 -> 없으면 등록		
		Map<String, Object> bookmark = new HashMap<String, Object>();
		bookmark.put("memberid", dto.getMemberid());
		bookmark.put("postnum", dto.getPostnum());
		
		int checkBookMark = service.checkBookMark(bookmark);
		if(checkBookMark == 0) {
			result = service.insertBookMark(dto);		
		}
		return result;
	}
	
	@PostMapping("/bookmarkdelete")
	@ResponseBody
	public int bookmarkdelete(BookMarkDTO dto) {
		int result = 0;
		//북마크 삭제하기
		//해당 북마크 있는지 한번 더 체크 -> 있으면 삭제
		Map<String, Object> bookmark = new HashMap<String, Object>();
		bookmark.put("memberid", dto.getMemberid());
		bookmark.put("postnum", dto.getPostnum());
		
		int checkBookMark = service.checkBookMark(bookmark);
		if(checkBookMark == 1) {
			result = service.deleteBookMark(dto);
		}
		return result;
	}
}
