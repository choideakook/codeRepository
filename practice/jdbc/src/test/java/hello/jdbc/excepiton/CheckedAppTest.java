package hello.jdbc.excepiton;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class CheckedAppTest {

    @Test
    void checked() {
        Controller controller = new Controller();
        assertThatThrownBy(() -> controller.request())
                .isInstanceOf(Exception.class);
    }

    @Test
    void printEx() {
        Controller controller = new Controller();
        try {
            controller.request();
        } catch (Exception e) {
            log.info("ex", e);
        }
    }

    static class Controller{
        Service service = new Service();

        public void request() {
            service.logic();
        }
    }
    static class Service{
        Repository repository = new Repository();
        NetworkClient networkClient = new NetworkClient();

        public void logic() {
            repository.call();
            networkClient.call();
        }
    }
    static class NetworkClient{
        public void call() {
            throw new RuntimeConnectException("연결 실패");
        }
    }
    static class Repository{
        public void call() {
            try {
                runSQL();
            } catch (SQLException e) {
                // RuntimeSQLException 을 만들 때
                // Cause Throwable 로 만들었기 때문에
                // 예외가 발생하면 이전 예외인 SQLException 의 설명이 출력되고
                // 속성은 RuntimeException 을 상속받아 Throw 를 선언하지 안아도 되게 변경되었다.
                throw new RuntimeSQLException(e);
            }
        }

        public void runSQL() throws SQLException {
            throw new SQLException("SQL Exception");
        }
    }
    // RuntimeException 로 변경
    static class RuntimeConnectException extends RuntimeException{
        // 단순하게 exception message 만 출력해줌
        public RuntimeConnectException(String message) {
            super(message);
        }
    }
    // RuntimeException 로 변경
    static class RuntimeSQLException extends RuntimeException {

        // 이전 예외까지 포함해서 message 를 출력해줌
        public RuntimeSQLException(Throwable cause) {
            super(cause);
        }
    }
}
