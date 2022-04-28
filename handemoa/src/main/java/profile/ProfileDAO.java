package profile;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("profiledao")
public interface ProfileDAO {
	
  	//사용자 및 관리자 공지 리스트 검색
  	public ProfileDTO memberintroduce(String nickname);
	
  	//사용자 및 관리자 공지 리스트 검색
  	public List<ProfileDTO> searchlist(SearchDTO search);
  	
  	//사용자 및 관리자 공지검색 총 갯수
  	public int searchcount(SearchDTO search);
  	
    //사용자 및 관리자 공지 리스트 검색
  	public List<ProfileDTO> commentlist(SearchDTO search);
  	
  	//사용자 및 관리자 공지검색 총 갯수
  	public int commentcount(SearchDTO search);
  	
}


