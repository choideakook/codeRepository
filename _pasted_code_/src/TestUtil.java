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
 