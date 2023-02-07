import java.util.ArrayList;
import java.util.Scanner;

public class Test2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean checker = true;

        while (true) {

            System.out.println("\n== 입력 ==");
            System.out.print("숫자 입력 : ");
            int num = Integer.parseInt(sc.next());

            if (num == -1) {
                System.out.println("== 종료 ==");
                break;
            }

            System.out.println("== 결과 ==");

            for (int i = 2; i <= num; i++) {
                checker = true;

                for (int j = 2; j < i; j++) {
                    if (i % j == 0) {
                        checker = false;
                        break;
                    }
                    continue;
                }
                if (checker == true)
                    System.out.println("- " + i);
            }
        }
    }
}
