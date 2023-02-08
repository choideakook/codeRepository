import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // 양의 정수를 입력받을 Scanner 생성
        Scanner sc = new Scanner(System.in);
        // 메모이제이션 기법을 사용하기 위한 Map 생성
        Map<Integer, Integer> db = new HashMap<>();

        // -1 을 하기 전까지 프로그램을 종료하지 않기위한 반복문 while
        while (true) {

            // 정수 입력 로직
            System.out.println("\n== 입력 ==");
            System.out.print("숫자 1 입력 : ");
            int num = Integer.parseInt(sc.next());

            // 만약 입력받은 정수가 -1 일경우 프로그램 종료
            if (num == -1) {
                System.out.println("== 종료==");
                break;
            }

            // 소수를 계산하는 로직
            System.out.println("== 결과 ==");

            for (int i = 2; i <= num; i++) {

                // 만약 이전에 계산된 소수값이 있을경우 바로 출력하고 현재 루프 건너뛰기
                if (db.get(i) != null) {
                    System.out.println("- " + i);
                    continue;
                }

                // 소수를 판별하기 위한 로직
                // checker true 로 초기화
                boolean checker = true;
                for (int j = 2; j < i; j++) {

                    // 만약 1과 자기 자신을 제외한 나머지 수로
                    // 한번이라도 나누어 떨어지면 false 그리고 현재 루프 종료
                    if (i % j == 0) {
                        checker = false;
                        break;
                    }
                    // 한번도 나누어 떨어지지 않았다면 true 인 상태로 루프 건너 뛰기
                    continue;
                }

                // true 인 값만 출력하고, 메모이제이션을 위해 Map 에 값을 put
                if (checker == true) {
                    System.out.println("- " + i);
                    db.put(i, i);
                }
            }
        }
    }
}