package com.example.servlet.web.servlet;

import com.example.servlet.domain.member.Member;
import com.example.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    MemberRepository repository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 정상적으로 호출되었는지 확인하기 위한 출력 로직
        System.out.println("MemberSaveServlet.service");

        //-- 클라이언트가 요청한 정보를 객체로 변환하는 작업 --//
        String username = request.getParameter("username");
        // getParameter 의 변수값은 항상 String 이기 때문에 int 로 캐스팅을 해주어야 한다.
        int age = Integer.parseInt(request.getParameter("age"));

        // 변환된 객체를 통해 DB 에 저장
        Member member = new Member(username, age);
        repository.save(member);

        //-- 응답 HTTP message --//
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        // 응답 message body
        PrintWriter w = response.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                "    <li>id="+member.getId()+"</li>\n" +
                "    <li>username="+member.getUsername()+"</li>\n" +
                "    <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");
    }
}
