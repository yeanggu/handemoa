package adminreport;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component("adminRCPageDTO")
public class AdminRCPageDTO {
	
	private int limitRows;	//한 페이지 출력 row
	private int limitPage;	//페이지 블럭 단위
	private int totalRows;  //총 레코드 개수
	private int totalPage;  //총 페이지 수
	private int currentpage; //현제 페이지
	private int currentBlock; //현재 페이지 블럭
	private int beginPage;  //현재 블럭 시작 페이지
	private int endPage;	//현재 블럭 마지막 페이지
	private boolean hasPrevPage; //이전 페이지 존재여브
	private boolean hasNextPage; //다음 페이지 존재여부
	
	public AdminRCPageDTO(int limitRows, int limitPage, int currentpage, int totalRows) {
		this.limitRows = limitRows;
		this.limitPage = limitPage;
		this.currentpage = currentpage;
		this.totalRows = totalRows;
		
		paging();
	}
	
	public void paging() {
		//출력할 내용 없을때
		if(this.totalRows == 0) {
			beginPage = 0;
			endPage = 0;
			totalPage = 0;
			hasPrevPage = false;
			hasNextPage = false;
			return;
		}
		totalPage = totalRows % limitRows == 0? (totalRows / limitRows):(totalRows / limitRows)+1;
		currentBlock = currentpage % limitPage == 0? (currentpage / limitPage) : (currentpage / limitPage) + 1;
		
		beginPage = ((currentBlock-1)  * limitPage) + 1;
		endPage = beginPage + limitPage - 1;
		if(endPage > totalPage) {
			endPage = totalPage;
		}

		hasPrevPage = beginPage == 1 ? false : true;
        if(hasPrevPage == false) {
            if(currentpage != beginPage) {
                hasPrevPage = true;
            }else {
                hasPrevPage = false;
            }
        }
 
        hasNextPage = (endPage == totalPage) ? false : true;
        if(hasNextPage == false) {
            //마지막 페이지에서 현재페이지가 마지막 페이지가 아닌경우 next처리
            if(currentpage != endPage) {
                hasNextPage = true;
            }else {
                hasNextPage = false;
            }
        }
	}
}
