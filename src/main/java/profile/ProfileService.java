package profile;

import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

public interface ProfileService {
	
  	
  	public ProfileDTO memberintroduce(String nickname);//사용자 및 관리자 공지 리스트 검색
  	public List<ProfileDTO> searchlist(SearchDTO search);//사용자 및 관리자 공지 리스트 검색
  	public int searchcount(SearchDTO search);//사용자 및 관리자 공지검색 총 갯수
    
  	public List<ProfileDTO> commentlist(SearchDTO search);//사용자 및 관리자 공지 리스트 검색
  	public int commentcount(SearchDTO search);//사용자 및 관리자 공지검색 총 갯수
}