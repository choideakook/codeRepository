package hello.jdbc.repository.ex;

// RuntimeException
public class MyDbException extends RuntimeException {
    // 기본 생성자
    public MyDbException() {}

    // exception message 출력
    public MyDbException(String message) {
        super(message);
    }

    // exception message 와 root cause 둘다 출력
    public MyDbException(String message, Throwable cause) {
        super(message, cause);
    }

    // root cause 출력
    public MyDbException(Throwable cause) {
        super(cause);
    }
}
