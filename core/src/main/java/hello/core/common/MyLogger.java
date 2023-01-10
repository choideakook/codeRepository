package hello.core.common;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope (value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    // requestURL 은 추후에 별도로 지정해주기 위해 setter 를 생성해줌
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        // 로그를 기록하기위한 출력물 생성
        System.out.println("[" + uuid + "] [" + requestURL + "] " + message);
    }

    // PostConstruct 로 초기화 콜백 하면서 uuid 를 생성해줌
    @PostConstruct
    public void init () {
        uuid = UUID.randomUUID().toString();
        // 초기화 콜백 message
        System.out.println("[" + uuid + "] request scope bean create : " + this);
    }
    // 소멸 콜백 message
    @PreDestroy
    public void close (){
        System.out.println("[" + uuid + "] request scope bean close : " + this);
    }
}
