package admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Repository;

import admin.AdminDTO;

@Mapper
@Repository("admindao")
public interface AdminDAO {
	
    public List<AdminDTO> sellectmemberlist(String quitType);
    public List<AdminDTO> sellectreportlist(String reportType);
    public List<AdminDTO> sellectpostlist(String postType);
    public List<AdminDTO> sellectnoticelist();
}


