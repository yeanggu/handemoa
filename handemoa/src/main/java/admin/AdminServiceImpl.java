package admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;


@Service("adminservice")
public class AdminServiceImpl implements AdminService {

	@Autowired
	@Qualifier("admindao")
	AdminDAO dao;
	
	@Override
	public List<AdminDTO> sellectmemberlist(String quitType){
		return dao.sellectmemberlist(quitType);
	}
	
	@Override
	public List<AdminDTO> sellectreportlist(String reportType){
		return dao.sellectreportlist(reportType);
	}
	
	@Override
	public List<AdminDTO> sellectpostlist(String postType){
		return dao.sellectpostlist(postType);
	}
	
	@Override
	public List<AdminDTO> sellectnoticelist(){
		return dao.sellectnoticelist();
	}
	
}
