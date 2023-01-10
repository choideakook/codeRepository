# To Do List 만들기 (java)
 <br>

 ## 기획
 * command guide 가 먼저 나오고 command ui 가 나옴 (Scanner 사용)
 * command 입력해서 array list 에 제목, 내용을 추가,삭제,수정,리스팅 할 수 있음
 * list 에 isDuplicate 로 제목 중복 검사해서 같은 제목은 저장이 안되게 함
 <br>
 준비물

 * main class : layout, command 기능
 * item class : 제목과 내용 setter geter method / getList 양식
 * list class : Array List / 추가,제거,리스팅,중복검사 method
 * util class : 추가기능, 제거기능, 수정기능, 리스팅기능 method
 * menu class : command guide method
 <br>

 1. main class

 ```java
 import java.util.Scanner;
 import com.test.util.TestList;
 import com.test.util.TestMenu;
 import com.test.util.TestUtil;

 public class TestMain {
 	
 	public static void start () {
 	Scanner sc = new Scanner(System.in);
 	TestList l = new TestList();
 	boolean quite = false;
 	
 	TestMenu.menu();
 	
 		do{
 			System.out.print("Commend > ");
 			String choice = sc.next();
 			switch (choice) {
 			case "add" :
 				TestUtil.addItem(l);
 				break;
 			case "delet" :
 				TestUtil.deletItem(l);
 				break;
 			case "list" :
 				TestUtil.listItem(l);
 				break;
 			case "edit" :
 				TestUtil.editItem(l);
 				break;
 			case "help" :
 				TestMenu.menu();
 				break;
 			case "out" :
 				quite = true;
 				System.out.println("System will Close\nThanks.");
 				break;
 			default :
 				System.out.println("Please input correct command.");
 			}
 		}while(!quite);
 	
 	}
 	
 }
 ```
 <br>
 2. item class

 ```java
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

 public class TestUtil {
 	
 	public static void addItem (TestList l) {
 		String title,desc;
 		Scanner sc = new Scanner (System.in);
 		System.out.print("Title > ");
 		title = sc.next();
 		if(l.isDuplicate(title)) {
 			System.out.println("already "+title+" was recorded");
 		return;
 		}
 		System.out.print("Desc > ");
 		desc = sc.next();
 		TestItem t = new TestItem (title,desc);
 		l.addItem(t);
 		System.out.println("Update Commplite");
 		
 	}
 	public static void deletItem (TestList l) {
 		String title;
 		Scanner sc = new Scanner (System.in);
 		System.out.print("Title > ");
 		title = sc.next();
 		for (TestItem item : l.getList()) {
 			if (title.equals(item.getTitle())) {
 				l.deletItem(item);
 				System.out.println("Title will Delet");
 				break;
 		}
 			}
 	}
 	public static void editItem (TestList l) {
 		Scanner sc = new Scanner (System.in);
 		System.out.print("Title to edit > ");
 		String title = sc.next();
 			if (!l.isDuplicate(title)) {
 				System.out.println("cannot found "+title);
 				return;
 			}
 		System.out.print("New Title > ");
 		String new_title = sc.next();
 		if(l.isDuplicate(new_title)) {
 			System.out.println(
 					"The title "+new_title+" was alread recorded");
 		return;}
 		System.out.print("New Desc > ");
 		String new_desc = sc.next();
 		for (TestItem item :l.getList()) {
 			if (title.equals(item.getTitle())) {
 				l.deletItem(item);
 		TestItem t =new TestItem(new_title,new_desc);
 		l.addItem(t);
 		System.out.println("Title was edited");
 		break;
 			}
 		}
 		
 		}
 	public static void listItem (TestList l) {
 		System.out.println("[List All]");
 		for(TestItem item : l.getList()) {
 			System.out.print(item.toString());
 		}
 	}

 }
 ```
 <br>
 5. menu class

 ```java
 public class TestMenu {
 	
 	public static void menu () {
 		System.out.println(
 				"[Wellcome to To Do List]\n"
 				+ "add item : add\n"
 				+ "delet item : delet\n"
 				+ "edit item : edit\n"
 				+ "list item : list\n"
 				+ "replay : help"
 				);
 	}

 }
 ```
