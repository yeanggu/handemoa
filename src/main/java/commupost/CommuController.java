package commupost;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import category1.CategoryDTO;
import category1.CategoryService;

@Controller
public class CommuController {
	
	@Autowired
	@Qualifier("commupostservice")
	CommuPostService service;
		
	@GetMapping("/community")
	public String commuView(int catedetailcode, int page, Model model) {		
		
		int viewPage = (page-1)*10;
		
		List<CommuPostVO> list = service.commuPostListViewInt(catedetailcode, viewPage);
		System.out.println(list.size()+"개의 게시물");
		model.addAttribute("postlist", list);
		
		//페이징처리
		List<CommuPostVO> listAll = service.allCommuPostListViewInt(catedetailcode);
		Paging paging = new Paging(page, 10, listAll.size());
		model.addAttribute("paging", paging);
		
		//랭킹순위 보드
		List<CommuBoardVO> boardlist = service.commuBoardListInt(catedetailcode);
		model.addAttribute("rankingboard", boardlist);
		
		//카테고리 네임
		model.addAttribute("catedetailcode", catedetailcode);
		
		return "community";
	}
	
	@GetMapping("/communitytempsave")
	public String commuTempView(HttpServletRequest request, int catedetailcode, int page, String memberid, Model model) {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("member") == null) {
			return "redirect:/login";
		}
		
		int viewPage = (page-1)*10;
		
		List<CommuPostVO> list = service.tempCommuPostListViewInt(catedetailcode, viewPage, 0, memberid);
		System.out.println(list.size()+"개의 게시물");
		model.addAttribute("postlist", list);
		
		//페이징처리
		List<CommuPostVO> listAll = service.tempAllCommuPostListViewInt(catedetailcode, 0, memberid);
		Paging paging = new Paging(page, 10, listAll.size());
		model.addAttribute("paging", paging);
		
		//랭킹순위 보드
		List<CommuBoardVO> boardlist = service.commuBoardListInt(catedetailcode);
		model.addAttribute("rankingboard", boardlist);
		
		//카테고리 네임
		model.addAttribute("catedetailcode", catedetailcode);
		
		return "community";
	}
	
	
	@GetMapping("/communitysearch")
	public String commuPostListViewSearch(int catedetailcode, int page, String postsearch, Model model) {		
		
		int viewPage = (page-1)*10;
		
		List<CommuPostVO> list = service.commuPostListViewSearch(catedetailcode, viewPage, postsearch);
		System.out.println(list.size()+"개의 게시물");
		model.addAttribute("postlist", list);
		
		//페이징처리
		List<CommuPostVO> listAll = service.allCommuPostListViewSearch(catedetailcode, postsearch);
		Paging paging = new Paging(page, 10, listAll.size());
		model.addAttribute("paging", paging);
		
		//랭킹순위 보드
		List<CommuBoardVO> boardlist = service.commuBoardListInt(catedetailcode);
		model.addAttribute("rankingboard", boardlist);
		
		//카테고리 네임
		model.addAttribute("catedetailcode", catedetailcode);
		
		//검색어
		model.addAttribute("postsearch", postsearch);
		
		return "community";
	}
	
	@GetMapping("/communitywrite")
	public String rankingWrite(HttpServletRequest request, int catedetailcode, Model model) {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("member") == null) {
			return "redirect:/login";
		}
		
		List<CommuBoardVO> boardlist = service.commuBoardListInt(catedetailcode);
		model.addAttribute("rankingboard", boardlist);
		
		model.addAttribute("catedetailcode", catedetailcode);
		
		return "communitywrite";
	}
	
	@GetMapping("/communitypost")
	public String commuPost(HttpServletRequest request, HttpServletResponse response, int postnum, Model model) {
		
		CommuPostVO vo = service.commuPostView(postnum);
		
		//조회수 증가 + 쿠키설정
	    Cookie oldCookie = null;
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("postView")) {
	                oldCookie = cookie;
	            }
	        }
	    }

	    if (oldCookie != null) {
	        if (!oldCookie.getValue().contains("[" + postnum + "]")) {
	        	service.commuViewCountUp(postnum);
	            oldCookie.setValue(oldCookie.getValue() + "_[" + postnum + "]");
	            oldCookie.setPath("/");
	            oldCookie.setMaxAge(60 * 60 * 24);
	            response.addCookie(oldCookie);
	        }
	    } else {
	    	service.commuViewCountUp(postnum);
	        Cookie newCookie = new Cookie("postView","[" + postnum + "]");
	        newCookie.setPath("/");
	        newCookie.setMaxAge(60 * 60 * 24);
	        response.addCookie(newCookie);
	    }
		
		System.out.println("받은 글번호:"+postnum);
		System.out.println("작성자 id:"+vo.getMemberid());
		model.addAttribute("vo", vo);
		
		List<CommuBoardVO> boardlist = service.commuBoardListInt(vo.catedetailcode);
		model.addAttribute("rankingboard", boardlist);
		
		//댓글 불러오기
		List<CommuCommentVO> commentlist = service.commucommentView(postnum);
		model.addAttribute("comment", commentlist);
		
		//게시물번호 불러오기
		model.addAttribute("postnum", postnum);
		//작성자 아이디
		model.addAttribute("memberid", vo.getMemberid());
		
		model.addAttribute("catedetailcode", vo.catedetailcode);
		
		//좋아요 싫어요 수
		int likecount = service.countlikecommupost(postnum, 1);
		int dislikecount = service.countlikecommupost(postnum, -1);
		model.addAttribute("likecount", likecount);
		model.addAttribute("dislikecount", dislikecount);
		
		return "communityboard";
	}
	
	@RequestMapping(value ="/communitypostlist", produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<CommuPostVO> commuPostListView(String catedetailname, Model model) {
		
		List<CommuPostVO> list = service.commuPostListView(catedetailname);
		return list;
	}
	
	@RequestMapping(value ="/communityboard", produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<CommuBoardVO> commuBoardList(String catedetailname, Model model) {
		
		List<CommuBoardVO> list = service.commuBoardList(catedetailname);
		return list;
	}
	
	@PostMapping("/commuinsert")
	@ResponseBody
	public int commuinsert(
						int catedetailcode,
						String author,
						String classtitle,
						String memberid,
						String posttitle,
						String content,
						String link,
						String thumbnail,
						int tempsave,
						Model model) {
		int row;
		CommuPostDTO dto = new CommuPostDTO();
		dto.setDivisioncode(1);
		dto.setCatedetailcode(catedetailcode);
		dto.setAuthor(author);
		dto.setClasstitle(classtitle);
		dto.setMemberid(memberid);
		dto.setPosttitle(posttitle);
		dto.setContent(content);
		dto.setLikecount(0);
		dto.setDislikecount(0);
		dto.setViewcount(0);
		dto.setTempsave(tempsave);
		dto.setHistory(0);

		if (link != null) {
			dto.setLink(link);
		}
		else {
			dto.setLink(null);			
		}
		if (thumbnail != null) {
			dto.setThumbnail(thumbnail);
		}
		else {
			dto.setThumbnail(null);		
		}
		row = service.commuinsert(dto);
		System.out.println("글번호:"+dto.getPostnum());
		return row;
	}
	
	@PostMapping("/commucommentinsert")
	@ResponseBody
	public int commucommentInsert(HttpServletRequest request, int postnum, String memberid, String commentcontent) {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("member") == null) {
			return -1;
		}
		
		int row;
		CommuCommentVO vo = new CommuCommentVO();
		vo.setDivisioncode(1);
		vo.setPostnum(postnum);
		vo.setMemberid(memberid);
		vo.setCommentcontent(commentcontent);
		vo.setLikecount(0);
		
		row = service.commucommentInsert(vo);
		System.out.println("댓글작성완료");
		return row;
	}
	
	@GetMapping("/commudeletepost")
	@ResponseBody
	public int commuDeletepost(HttpServletRequest request, int postnum, String postid, String userid, Model model) {
		
		HttpSession session = request.getSession();

		if (session.getAttribute("member") == null) {
			return -1;
		}
		if (!postid.equals(userid)) {
			return -2;
		}
		
		int row = service.commuDeletepost(postnum);
		return row;
	}

	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String communityupdate(CommuPostDTO dto){ // 커뮤니티 게시글 수정 후 커뮤메인 이동
		int result1 = service.communityupdate(dto); // 1= 성공
		return "redirect:community?catedetailcode=8&page=1";
	}
	
	@RequestMapping("/update") // 커뮤니티 게시글 수정폼
	public ModelAndView communityupdate2(int postnum, HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		HttpSession session = request.getSession();
	      
	      if (session.getAttribute("member") == null) {
	    	  mv.setViewName("redirect:/login");
	    	  return mv;
	      }
		
	    CommuPostVO vo = service.commuPostView(postnum);
		mv.addObject("communitydto", vo);
		mv.setViewName("communitypostupdate");
		return mv;
	}
	
	@GetMapping("/likecommupost")
	@ResponseBody
	public int[] likepost(HttpServletRequest request, int postnum, String userid, int likestatus, Model model) {
		
		HttpSession session = request.getSession();
		
		int [] result = new int [2];

		if (session.getAttribute("member") == null) {
			result[0] = -1;
			result[1] = -1;
			return result;
		}
		System.out.println("좋아요 시스템 실행");
		if (likestatus == 1) {
			int row = service.likecommupost(postnum, userid, 1);
			System.out.println("좋아요 실행");
		}
		else if (likestatus == -1) {
			int row = service.likecommupost(postnum, userid, -1);
			System.out.println("싫어요 실행");
		}
		
		int likecount = service.countlikecommupost(postnum, 1);
		int dislikecount = service.countlikecommupost(postnum, -1);
		
		result[0] = likecount;
		result[1] = dislikecount;
		
		return result;		
	}
	
}
