package index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bookmark.ViewPageDTO;
import category1.CategoryDTO;
import rankpost.RankBoardVO;
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
		List<CategoryDTO> cateAll = service.searchCateAll();
		List<IndexDTO> reportlist = service.sellectreportlist(reportType);
		List<IndexDTO> postlist = service.sellectpostlist(postType);
		List<IndexDTO> noticelist = service.sellectnoticelist();
		mv.addObject("catename", catename);
		mv.addObject("cateAll", cateAll);
		mv.addObject("reportlist", reportlist);
		mv.addObject("postlist", postlist);
		mv.addObject("noticelist", noticelist);
        mv.setViewName("index");
        return mv;
	}
	
	@RequestMapping("/search2")
	   public ModelAndView search2() {
	      ModelAndView mv = new ModelAndView();   
	      
	      int catedetailcode = service.catedetailcode(); 
	      List<RankBoardVO> boardlist = service.rankBoardListInt(catedetailcode);
	  		System.out.println(catedetailcode);
	      mv.addObject("rankingboard", boardlist);
	      mv.setViewName("index");
	      return mv;
	   }
	
	
	
	
	
	
	
	
}
