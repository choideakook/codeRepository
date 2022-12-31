package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping ("hello")   // <- web url 의 / 다음의 단어와 동일할경우 해당 method 실행
    public String hello (Model model){
        model.addAttribute("data", "hello!!");
            return "hello";   // <- templates 의 html 파일명 입력 (vise resolver 가 해당 파일에 method 를 적용시킴)
    }

    /**
     *  MVC 와 탬플릿엔진 방식
     */
    @GetMapping ("hello-mvc")
    public String helloMvc (@RequestParam(name = "name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    /**
     *  API 방식 (문자열 출력 방식 )
     */
    @GetMapping ("hello-spring")
    @ResponseBody       // <- api 방식의 표시 (templates 의 html 파일 없이 바로 web에 출력됨)
    public String helloString (@RequestParam("webName") String value, Model model){
        return "hello " + value;    // <- html 파일 없이 바로 hello value 를 사이트에 출력함
    }

    /**
     *  API 방식 (Data 출력 방식 : json 사용)
     */
    @GetMapping ("hello-api")
    @ResponseBody
    public Hello helloApi (@RequestParam ("web") String value){

        // get 과 set 을 하기위해 instance 생성
        Hello hello = new Hello();
        // setter 로 key 값에 value 를 넣어줌
        hello.setKey(value);
        return hello;
    }

    // getter setter Class
    static class Hello {
        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}