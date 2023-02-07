import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input = "";

        while (true) {
            System.out.print("숫자 1 입력 : ");
            input = sc.next();
            if (input.contains(".")) {
                System.out.println("입력오류 : 정수로 입력해주세요.");
            } else {
                break;
            }
        }
        int num1 = Integer.parseInt(input);

        while (true) {
            System.out.print("숫자 2 입력 : ");
            input = sc.next();
            if (input.contains(".")) {
                System.out.println("입력오류 : 정수로 입력해주세요.");
            } else if (input.equals("0")) {
                System.out.println("입력오류 : 두번째 숫자는 0이 될 수 없습니다.");
            } else {
                break;
            }
        }
        int num2 = Integer.parseInt(input);


        System.out.println(
                "== 결과 ==\n" +
                "- 합 : " + (num1 + num2)+ "\n" +
                "- 차 : " + (num1 - num2)+ "\n" +
                "- 곱 : " + (num1 * num2)+ "\n" +
                "- 나눈 몫 : " + (num1 / num2)+ "\n" +
                "- 나눈 나머지 : " + (num1 % num2)+ "\n"
        );
    }
}