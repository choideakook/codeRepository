public class TestItem {
 	
 	private String title, desc;
 	
 	public TestItem (String title, String desc) {
 		this.title = title ;
 		this.desc = desc ;
 		
 	}
 	
 	public String getTitle () {
 		return title;
 	}
 	public void setTitle(String title) {
 		this.title = title;
 	}
 	public String getDesc () {
 		return desc;
 	}
 	public void setDesc(String desc) {
 		this.desc = desc;
 	}
 	public void getList() {
 		
 	}
 	
 	@Override
 	public String toString () {
 		return "Title : " + title +" / Desc : " + desc+ "\n";
 	}
 	
 	

 }
 ```
 <br>
 3. list class

 ```java
 import java.util.ArrayList;

 