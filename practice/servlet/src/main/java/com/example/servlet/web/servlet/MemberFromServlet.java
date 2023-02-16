package com.example.servlet.web.servlet;

import com.example.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberFromServlet",urlPatterns = "/servlet/members/new-form")
public class MemberFromServlet extends HttpServlet {

    MemberRepository repository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //-- HTTP 응답 message --//
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        //-- HTTP 응답 message body --//
        PrintWriter writer = response.getWriter();

        // 이제 html 을 작성해야하는데
        // java 코드를 통해 html 을 작성해야하기 때문에
        // 이 방법은 매우 불편하고 오타의 확률이 매우 크다.
        writer.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/servlet/members/save\" method=\"post\">\n" +
                "    username: <input type=\"text\" name=\"username\" />\n" +
                "    age:      <input type=\"text\" name=\"age\" />\n" +
                "    <button type=\"submit\">전송</button>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>\n");
    }
}
