public class TestList {
 	
 	private ArrayList <TestItem> list;
 	
 	public TestList () {
 		this.list = new ArrayList <TestItem>();
 	}
 	
 	public void addItem (TestItem t ) {
 		list.add(t);
 	}

 	public void deletItem (TestItem t) {
 		list.remove(t);
 	}
 	public ArrayList<TestItem> getList (){
 		return new ArrayList <TestItem>(list);
 	}
 	
 	public Boolean isDuplicate (String title) {
 		for(TestItem item : list) {
 			if (title.equals(item.getTitle()))
 				return true;
 		}
 		return false;
 	}
 }
 ```
 <br>

 4.util class

 ```java
 import java.util.Scanner;

 