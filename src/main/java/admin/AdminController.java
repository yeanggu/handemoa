package admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AdminController {
	
	@Autowired
	@Qualifier("adminservice")
	AdminService service;
	
	@RequestMapping(value = "/adminindex", method = RequestMethod.GET)
    public ModelAndView AllListView(@RequestParam(required = false) String quitType,
    		@RequestParam(required = false) String reportType,
    		@RequestParam(required = false) String postType
    		) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<AdminDTO> memberlist = service.sellectmemberlist(quitType);
		List<AdminDTO> reportlist = service.sellectreportlist(reportType);
		List<AdminDTO> postlist = service.sellectpostlist(postType);
		List<AdminDTO> noticelist = service.sellectnoticelist();
		mv.addObject("memberlist", memberlist);
		mv.addObject("reportlist", reportlist);
		mv.addObject("postlist", postlist);
		mv.addObject("noticelist", noticelist);
        mv.setViewName("adminindex");
        return mv;
	}
	
	
	@RequestMapping("/loginindex")
	public ModelAndView loginindex() {
		ModelAndView mv= new ModelAndView();
		mv.addObject("model", "index");
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("/member")
	public ModelAndView member() {
		ModelAndView mv= new ModelAndView();
		mv.addObject("model", "member");
		mv.setViewName("member");
		return mv;
	}
	
	
}
