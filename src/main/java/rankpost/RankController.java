package rankpost;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import bookmark.BookMarkService;
import category1.CategoryDTO;
import category1.CategoryService;
import member.MemberDTO;

@Controller
public class RankController {
	
	@Autowired
	@Qualifier("rankpostservice")
	RankPostService service;
	
	@Autowired
	@Qualifier("bookmarkservice")
	BookMarkService bookmarkservice;
		
	@GetMapping("/ranking")
	public String rankingView(int catedetailcode, int page, Model model) {		
		
		int viewPage = (page-1)*10;
		
		List<RankPostVO> list = service.rankPostListViewInt(catedetailcode, viewPage, 1);
		System.out.println(list.size()+"개의 게시물");
		model.addAttribute("postlist", list);
		
		//페이징처리
		List<RankPostVO> listAll = service.allRankPostListViewInt(catedetailcode, 1);
		Paging paging = new Paging(page, 10, listAll.size());
		model.addAttribute("paging", paging);
		
		//랭킹순위 보드
		List<RankBoardVO> boardlist = service.rankBoardListInt(catedetailcode);
		model.addAttribute("rankingboard", boardlist);
		
		//카테고리 네임
		model.addAttribute("catedetailcode", catedetailcode);
		
		return "ranking";
	}
	
	@GetMapping("/rankingtempsave")
	public String rankingTempView(HttpServletRequest request, int catedetailcode, int page, String memberid, Model model) {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("member") == null) {
			return "redirect:/login";
		}
		
		int viewPage = (page-1)*10;
		
		List<RankPostVO> list = service.tempRankPostListViewInt(catedetailcode, viewPage, 0, memberid);
		System.out.println(list.size()+"개의 게시물");
		model.addAttribute("postlist", list);
		
		//페이징처리
		List<RankPostVO> listAll = service.tempAllRankPostListViewInt(catedetailcode, 0, memberid);
		Paging paging = new Paging(page, 10, listAll.size());
		model.addAttribute("paging", paging);
		
		//랭킹순위 보드
		List<RankBoardVO> boardlist = service.rankBoardListInt(catedetailcode);
		model.addAttribute("rankingboard", boardlist);
		
		//카테고리 네임
		model.addAttribute("catedetailcode", catedetailcode);
		
		return "ranking";
	}
	
	@GetMapping("/rankingsearch")
	public String rankPostListViewSearch(int catedetailcode, int page, String postsearch, Model model) {		
		
		int viewPage = (page-1)*10;
		
		List<RankPostVO> list = service.rankPostListViewSearch(catedetailcode, viewPage, postsearch);
		System.out.println(list.size()+"개의 게시물");
		model.addAttribute("postlist", list);
		
		//페이징처리
		List<RankPostVO> listAll = service.allRankPostListViewSearch(catedetailcode, postsearch);
		Paging paging = new Paging(page, 10, listAll.size());
		model.addAttribute("paging", paging);
		
		//랭킹순위 보드
		List<RankBoardVO> boardlist = service.rankBoardListInt(catedetailcode);
		model.addAttribute("rankingboard", boardlist);
		
		//카테고리 네임
		model.addAttribute("catedetailcode", catedetailcode);
		
		//검색어
		model.addAttribute("postsearch", postsearch);
		
		return "ranking";
	}
	
	@GetMapping("/rankingwrite")
	public String rankingWrite(HttpServletRequest request, int catedetailcode, Model model) {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("member") == null) {
			return "redirect:/login";
		}
		
		List<RankBoardVO> boardlist = service.rankBoardListInt(catedetailcode);
		model.addAttribute("rankingboard", boardlist);
		
		model.addAttribute("catedetailcode", catedetailcode);
		
		return "rankingwrite";
	}
	
	@GetMapping("/rankingedit")
	public String editPost(HttpServletRequest request, int postnum, Model model) {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("member") == null) {
			return "redirect:/login";
		}
		
		RankPostVO vo = service.rankPostView(postnum);
		model.addAttribute("vo", vo);
		
		//게시물번호 불러오기
		model.addAttribute("postnum", postnum);
		//작성자 아이디
		model.addAttribute("memberid", vo.getMemberid());
		//카테고리 번호
		model.addAttribute("catedetailcode", vo.catedetailcode);
		
		//랭킹순위 보드
		List<RankBoardVO> boardlist = service.rankBoardListInt(vo.catedetailcode);
		model.addAttribute("rankingboard", boardlist);
		
		return "rankingedit";
	}
	
	@GetMapping("/rankingpost")
	public String rankingPost(HttpServletRequest request, HttpServletResponse response, int postnum, Model model) {
		
		RankPostVO vo = service.rankPostView(postnum);
		
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
	        	service.rankingViewCountUp(postnum);
	            oldCookie.setValue(oldCookie.getValue() + "_[" + postnum + "]");
	            oldCookie.setPath("/");
	            oldCookie.setMaxAge(60 * 60 * 24);
	            response.addCookie(oldCookie);
	        }
	    } else {
	    	service.rankingViewCountUp(postnum);
	        Cookie newCookie = new Cookie("postView","[" + postnum + "]");
	        newCookie.setPath("/");
	        newCookie.setMaxAge(60 * 60 * 24);
	        response.addCookie(newCookie);
	    }
			
		model.addAttribute("vo", vo);
				
		List<RankBoardVO> boardlist = service.rankBoardListInt(vo.catedetailcode);
		model.addAttribute("rankingboard", boardlist);
		
		//댓글 불러오기
		List<CommentVO> commentlist = service.commentView(postnum);
		model.addAttribute("comment", commentlist);
		
		//게시물번호 불러오기
		model.addAttribute("postnum", postnum);
		//작성자 아이디
		model.addAttribute("memberid", vo.getMemberid());
		//카테고리 번호
		model.addAttribute("catedetailcode", vo.catedetailcode);
		
		//좋아요 싫어요 수
		int likecount = service.countlikepost(postnum, 1);
		int dislikecount = service.countlikepost(postnum, -1);
		model.addAttribute("likecount", likecount);
		model.addAttribute("dislikecount", dislikecount);
		model.addAttribute("checkBookMark", bookmarkcheck(request, postnum));
		
		return "rankingpost";
	}
	
	
	   public int bookmarkcheck(HttpServletRequest request, int postnum) {
		      int checkBookMark = 0;
		      HttpSession session = request.getSession();
		      String loginid = null;
		      //로그인 여부 체크      
		      if (session.getAttribute("member") == null) {
		         checkBookMark = 0;
		      }
		      else{
		         MemberDTO logindto = (MemberDTO) session.getAttribute("member");
		         loginid = logindto.getId();
		         
		         Map<String, Object> bookmark = new HashMap<String, Object>();
		         bookmark.put("memberid", loginid);
		         bookmark.put("postnum", postnum);
		         
		         checkBookMark = bookmarkservice.checkBookMark(bookmark);
		      }         
		      
		      return checkBookMark;
		   }
	
	@RequestMapping(value ="/rankpostlist", produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<RankPostVO> rankPostListView(String catedetailname, Model model) {
		
		List<RankPostVO> list = service.rankPostListView(catedetailname);
		return list;
	}
	
	@RequestMapping(value ="/rankboard", produces="application/json;charset=UTF-8")
	@ResponseBody
	public List<RankBoardVO> rankBoardList(String catedetailname, Model model) {
		
		List<RankBoardVO> list = service.rankBoardList(catedetailname);
		return list;
	}
	
	@PostMapping("/insert")
	@ResponseBody
	public int insert(
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
		RankPostDTO dto = new RankPostDTO();
		dto.setDivisioncode(2);
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
		row = service.insert(dto);		
			
		model.addAttribute("fine", "fine");
		return row;
	}
	
	@PostMapping("/editpostupdate")
	@ResponseBody
	public int editPostUpdate(
						int postnum,
						String author,
						String classtitle,
						String posttitle,
						String content,
						String link,
						String thumbnail,
						int tempsave,
						Model model) {
		int row;
		RankPostDTO dto = new RankPostDTO();
		dto.setPostnum(postnum);
		dto.setAuthor(author);
		dto.setClasstitle(classtitle);
		dto.setPosttitle(posttitle);
		dto.setContent(content);
		dto.setTempsave(tempsave);
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
		row = service.editRankingPost(dto);
		
		return row;
	}
	
	@PostMapping("/commentinsert")
	@ResponseBody
	public int commentInsert(HttpServletRequest request, int postnum, String memberid, String commentcontent, String postmemberid) {
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("member") == null) {
			return -1;
		}
		
		int row;
		CommentVO vo = new CommentVO();
		vo.setDivisioncode(2);
		vo.setPostnum(postnum);
		vo.setMemberid(memberid);
		vo.setCommentcontent(commentcontent);
		vo.setLikecount(0);
		
		//댓글 알람기능
		AlarmDTO alarm = new AlarmDTO();
		alarm.setId(postmemberid);
		alarm.setCaller(memberid);
		alarm.setPostnum(postnum);
		alarm.setAlarmcontent(commentcontent);
		if ( alarm.getId().equals(alarm.getCaller()) ) {
			alarm.setRead_al(true);
		}
		else {
			alarm.setRead_al(false);
		}		
		int row2 = service.insertAlarm(alarm);
		System.out.println("읽음유무: "+alarm.isRead_al());
		
		row = service.commentInsert(vo);
		System.out.println("댓글작성완료");
		return row;
	}
	
	@GetMapping("/deletepost")
	@ResponseBody
	public int postDelete(HttpServletRequest request, int postnum, String postid, String userid, Model model) {
		
		HttpSession session = request.getSession();

		if (session.getAttribute("member") == null) {
			return -1;
		}
		if (!postid.equals(userid)) {
			return -2;
		}
		
		int row = service.postDelete(postnum);
		return row;
	}
	
	@PostMapping("/uploadfile")
	@ResponseBody
	public String uploadresult(MultipartFile thumbnail, Model model) throws IOException{
		
		String thumbnailName = null;
		
		MultipartFile mf1 = thumbnail;
		System.out.println("getOriginalFilename:"+mf1.getOriginalFilename()); //업로드한 파일이름
		System.out.println("getSize:"+mf1.getSize()); //길이
		System.out.println("isEmpty:"+mf1.isEmpty()); //파일선택여부

		// 파일내용+파일명--> 서버 c:/upload 폴더 영구 저장
		String savePath = "/usr/mydir/tomcat/webapps/ROOT/WEB-INF/classes/static/css/images/";
		
		if(!mf1.isEmpty()) {
			//원래파일명
			String originname1 = mf1.getOriginalFilename();
			//확장자 이전파일 명
			String beforeext1 = originname1.substring(0, originname1.indexOf("."));
			//확장자 이후
			String ext1 = originname1.substring(originname1.indexOf("."));
			//경로 뺀 이름+확장자
			thumbnailName = beforeext1+"("+UUID.randomUUID().toString()+")"+ext1;
			System.out.println(thumbnailName);
			File serverfile1 = new File(savePath + thumbnailName); 
			mf1.transferTo(serverfile1);
		} else {
			return "default_thumbnail.png";
		}
		
		return thumbnailName;
	}
	
	@GetMapping("/likepost")
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
			int row = service.likepost(postnum, userid, 1);
			System.out.println("좋아요 실행");
		}
		else if (likestatus == -1) {
			int row = service.likepost(postnum, userid, -1);
			System.out.println("싫어요 실행");
		}
		
		int likecount = service.countlikepost(postnum, 1);
		int dislikecount = service.countlikepost(postnum, -1);
		
		result[0] = likecount;
		result[1] = dislikecount;
		
		return result;		
	}
	
	@GetMapping("/alarm")
	@ResponseBody
	public List<AlarmDTO> alarmList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();		
		
		if (session.getAttribute("member") == null) {
			return null;
		}
		MemberDTO memberdto = (MemberDTO) session.getAttribute("member");
		System.out.println("id :"+memberdto.getId());
		
		List<AlarmDTO> list = service.alarmList(memberdto.getId());
		
		return list;
	}
	
	@GetMapping("/alarmread")
	@ResponseBody
	public int alarmRead(HttpServletRequest request, int alarmnum, Model model) {
		return service.alarmRead(alarmnum);
	}
	
	@GetMapping("/deleteallalarm")
	@ResponseBody
	public int deleteAllAlarm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();		
		
		if (session.getAttribute("member") == null) {
			return 0;
		}
		MemberDTO memberdto = (MemberDTO) session.getAttribute("member");
		int row = service.deleteAllAlarm(memberdto.getId());
		System.out.println(row+"개의 알람 데이터 삭제");
		
		return 1;
	}
	
}
