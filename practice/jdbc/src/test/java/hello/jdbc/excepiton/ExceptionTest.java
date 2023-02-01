package hello.jdbc;

import org.junit.jupiter.api.Test;

public class ExceptionTest {

    @Test
    void main() {
        int[] array = {0};

        System.out.println(1);

        try {
            System.out.println(2);
            System.out.println(2 / 0);
            System.out.println(3);
        } catch (ArithmeticException e) {
            System.out.println("계산이 잘못되었습니다." + e.getMessage());
        }

        System.out.println(3);
    }
}
