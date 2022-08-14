package index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import bookmark.ViewPageDTO;
import category1.CategoryDTO;
import rankpost.Paging;
import rankpost.RankBoardVO;
import rankpost.RankPostVO;
import search.SearchPostJoinDTO;


@Controller
public class IndexController {
	
	@Autowired
	@Qualifier("indexservice")
	IndexService service;
	
	
	@RequestMapping(value = "/handemoa", method = RequestMethod.GET)
    public ModelAndView AllListView(
    		@RequestParam(required = false) String reportType,
    		@RequestParam(required = false, defaultValue = "community") String postType
    		) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<String> catename = service.searchCate();
		List<IndexDTO> reportlist = service.sellectreportlist(reportType);
		List<IndexDTO> postlist = service.sellectpostlist(postType);
		List<IndexDTO> noticelist = service.sellectnoticelist();
		mv.addObject("catename", catename);
		mv.addObject("reportlist", reportlist);
		mv.addObject("postlist", postlist);
		mv.addObject("noticelist", noticelist);
        mv.setViewName("index");
        return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/maincategory")
	   public List<CategoryDTO> search(@RequestParam(required = false) String catename,Model model) {
		  List<CategoryDTO> cateAll = service.searchCateAll(catename);
	      return cateAll;
	   }
	
	@ResponseBody
	@RequestMapping(value = "/mainranking")
	   public List<RankBoardVO> mainranking(@RequestParam(required = false) int catedetailcode,Model model) {
			//랭킹순위 보드
			List<RankBoardVO> boardlist = service.rankBoardListInt(catedetailcode);
			model.addAttribute("rankingboard", boardlist);
	      return boardlist;
	   }
	
	
	
}
