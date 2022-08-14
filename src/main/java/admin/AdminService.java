package admin;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

public interface AdminService {
	
    public List<AdminDTO> sellectmemberlist(String quitType);
    public List<AdminDTO> sellectreportlist(String reportType);
    public List<AdminDTO> sellectpostlist(String postType);
    public List<AdminDTO> sellectnoticelist();
}