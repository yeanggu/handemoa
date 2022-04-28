package profile;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component("profilesearchdto")
public class SearchDTO extends PageDTO{
	private String searchType;
	private String keyword;
	
	private String searchTypeKeyword; 

	public void setSearchTypeKeyword(String searchType, String keyword) {
	 
	 if(searchType.equals("") || keyword.equals("")) {
	  searchTypeKeyword = ""; 
	 } else {
	  searchTypeKeyword = "&searchType=" + searchType + "&keyword=" + keyword; 
	 }  
	}

	public String getSearchTypeKeyword() {
	 return searchTypeKeyword;
	}	
	
}
