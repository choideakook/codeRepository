package com.example.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //--- status line ---//
        // HTTP 응답 코드를 넣을 수 있음
        // parameter 에 코드 숫자 (200) 을 직접 넣어도 가능하지만
        // 되도록 상수로 지정된 변수를 사용하는것이 좋다.
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        //--- header ---//
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        // 케시 설정 (무효화하는 설정을 했음)
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        // 과거 버전인 HTTP 0.1 까지 모든 케시를 무효화함
        response.setHeader("Pragma", "no-cache");
        // 내가 원하는 임의의 header 정보도 작성할 수 있음
        response.setHeader("my-header", "hello");

        //-- content 관련 편의 method --//
//        content(response);

        //-- 쿠키 관련 편의 method --//
        cookie(response);

        //-- redirect 편의 method --//
        redirect(response);

        PrintWriter writer = response.getWriter();
        writer.println("Ok");
    }

    private void content(HttpServletResponse response) {
        // 방법 1.
        // Content-Type: text/plain;charset=utf-8
        // 방법 2.
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        // 방법 3.
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        // content-length 생략시 자동 생성
        // 직접 작성할경우 작성된 값이 출력됨
        // response.setContentLength(2);
    }

    private void cookie(HttpServletResponse response) {
        // 방법 1.
        // Set-Cookie: myCookie=good; Max-Age=600;
        // 방법 2.
        // response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        // 방법 3.
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //-- 목표 --//
        // Status Code 302
        // Location: /basic/hello-form.html

        // 방법 1.
//        response.setStatus(HttpServletResponse.SC_FOUND); //302
        // 리다이렉트로 인해 해당 url 로 자동으로 이동한다.
//        response.setHeader("Location", "/basic/hello-form.html");
        // 방법 2.
        response.sendRedirect("/basic/hello-form.html");
    }
}
