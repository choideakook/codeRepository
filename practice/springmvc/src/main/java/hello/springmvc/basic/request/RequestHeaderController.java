package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request, // 요청 값
                          HttpServletResponse response, // 응답
                          HttpMethod httpMethod, // http method
                          Locale locale, // 언어 정보 (가장 우선순위가 높은 언어가 변수에 담긴다.)
                          @RequestHeader MultiValueMap<String, String> headerMap, // 모든 header 정보 조회
                          @RequestHeader("host") String host, // 특정 header 정보 조회 (host) 를 조회함
                          @CookieValue(value = "myCookie", required = false) String cookie // 쿠기 조회
    ){
        log.info("request = {}", request);
        log.info("response = {}", response);
        log.info("httpMethod = {}", httpMethod);
        log.info("locale = {}", locale);
        log.info("headerMap = {}", headerMap);
        log.info("header host = {}", host);
        log.info("myCookie = {}", cookie);

        return "ok";
    }
}
