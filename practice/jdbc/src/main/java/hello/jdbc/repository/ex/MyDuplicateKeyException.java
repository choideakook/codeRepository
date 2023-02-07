package hello.jdbc.repository.ex;

// MyDbException 을 상속시키면 MyDbException 에서 파상된 Exception 인걸 쉽게 확인할 수 있다.
public class MyDuplicateKeyException extends MyDbException{
    public MyDuplicateKeyException() {
    }

    public MyDuplicateKeyException(String message) {
        super(message);
    }

    public MyDuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDuplicateKeyException(Throwable cause) {
        super(cause);
    }
}
