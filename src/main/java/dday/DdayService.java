package dday;

import java.util.List;

public interface DdayService {

	//디데이 목록 조회
	public List<DdayDTO> ddayList(String memberid);
	//디데이 생성
	public int insertDday(DdayDTO dto);
	//디데이 삭제
	public int deleteDday(List<Integer> ddaynums);
	//디데이 수정
	public int updateDday(DdayDTO dto);
}
