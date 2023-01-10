package hello.core.lifecycle;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    // 접속해야 할 서버의 url
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    // 외부에서 url 을 setter 로 입력할 수 있게 해줌
    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출되는 method
    public void connect () {
        System.out.println("connect " + url);
    }

    // 연결이 된 상태에서 Call 할 수 있는 기능
    public void call (String message) {
        System.out.println("Call : " + url + " , message = " + message);
    }

    // 서비스 종료시 호출하는 method
    public void disconnect () {
        System.out.println("close : " + url);
    }

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
