package commupost;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paging {
	
	private int page;
	private int pageViewSize;
	private int totalPage;
	private int startPage;
	private int endPage;
	private int previousPageStart;
	private int nextPageStart;
	private int firstPage;

	
	public Paging(int page, int pageViewSize, int total) {
		this.page = page;
		this.pageViewSize = pageViewSize;
		this.totalPage = total % 10 == 0 ? (total / pageViewSize) : (total / pageViewSize)+1;
		
		this.firstPage = 1;
		
		setEndPage(calEndPage(page));
		setStartPage(calStartPage(page));
		
		setPreviousPageStart(calPreviousPageStart(page));
		setNextPageStart(calNextPageStart(page));
	}
	
	
	private int calEndPage(int page) {
		int answer = (int)(Math.ceil(page*1.0/pageViewSize))*pageViewSize;
		return answer > totalPage ? totalPage : answer;
	}
	
	private int calStartPage(int page) {
		if ((endPage - (pageViewSize-1)) <= 1) {
			return 1;
		}
		return ((page/pageViewSize)*pageViewSize)+1;		
	}
	
	private int calPreviousPageStart(int page) {
		if (page >= firstPage && page <= pageViewSize) {
			return -1;
		}
		return (((page/pageViewSize)-1)*pageViewSize)+1;
	}
	
	private int calNextPageStart(int page) {
		if (page > (totalPage/pageViewSize)*pageViewSize) {
			return -1;
		}
		return (int)(Math.ceil(page*1.0/pageViewSize))*pageViewSize+1;
	}
}
