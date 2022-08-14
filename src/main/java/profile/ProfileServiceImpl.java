package profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;


@Service("profileservice")
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	@Qualifier("profiledao")
	ProfileDAO dao;
	
	
	//사용자 및 관리자 공지 리스트 검색
	@Override
	public ProfileDTO memberintroduce(String nickname) {
		return dao.memberintroduce(nickname);
	}	
	
	
	//사용자 및 관리자 공지 리스트 검색
	@Override
	public List<ProfileDTO> searchlist(SearchDTO search) {
		return dao.searchlist(search);
	}
	
	//사용자 및 관리자 공지검색 총 갯수
	@Override
	public int searchcount(SearchDTO search) {
		return dao.searchcount(search);
	}

	//사용자 및 관리자 공지 리스트 검색
	@Override
	public List<ProfileDTO> commentlist(SearchDTO search) {
		return dao.commentlist(search);
	}
	
	//사용자 및 관리자 공지검색 총 갯수
	@Override
	public int commentcount(SearchDTO search) {
		return dao.commentcount(search);
	}

	
	
}
