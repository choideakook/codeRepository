To Do List 만들기 (java)
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
 