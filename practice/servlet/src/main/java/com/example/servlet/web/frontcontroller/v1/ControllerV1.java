package com.example.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 {

    // servlet 의 service method 와 동일한 모양으로 interface 객체 생성
    // Front Controller 는 interface 를 호출해 구현화 관계없이 로직의 일관성을 가져갈 수 있음
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;


}
