package hello.jdbc.excepiton;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class CheckedTest {

    @Test
    void checked_catch() {
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void checked_throw(){
        Service service = new Service();
        assertThatThrownBy(() -> service.callThrow())
                .isInstanceOf(MyCheckedException.class);

    }

    static class MyCheckedException extends Exception {

        /**
         * Exception 을 상속받은 예외는 체크 예외가 된다.
         */
        public MyCheckedException(String message) {
            super(message);
        }
    }

    static class Service{
        Repository repository = new Repository();

        // 예외를 던지는 코드
        public void callThrow () throws MyCheckedException {
            repository.call();
        }

        // 예외를 처리하는 코드
        public void callCatch() {
            try {
                repository.call();
            } catch (MyCheckedException e) {
                // 예외 처리 로직
                log.info("예외 처리, message = {}", e.getMessage(), e);
            }
        }
    }

    static class Repository{
        public void call() throws MyCheckedException {
            throw new MyCheckedException("ex");
        }
    }

}
