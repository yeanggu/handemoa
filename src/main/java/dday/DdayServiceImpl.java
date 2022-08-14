package dday;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("ddayservice")
public class DdayServiceImpl implements DdayService {

	@Autowired
	@Qualifier("ddayDAO")
	DdayDAO dao;
	
	@Override
	public List<DdayDTO> ddayList(String memberid) {
		return dao.ddayList(memberid);
	}

	@Override
	public int insertDday(DdayDTO dto) {
		return dao.insertDday(dto);
	}

	@Override
	public int deleteDday(List<Integer> ddaynums) {
		return dao.deleteDday(ddaynums);
	}

	@Override
	public int updateDday(DdayDTO dto) {
		return dao.updateDday(dto);
	}

}
