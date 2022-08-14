package category1;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
	
	String catename;
	String catedetailname;
	String catedetailcode;

	CategoryDTO(String catename) {
		this.catename = catename;
	}
	
}
