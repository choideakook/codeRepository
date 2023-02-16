package com.example.servlet.web.servletmvc;

import com.example.servlet.domain.member.Member;
import com.example.servlet.domain.member.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

    private MemberRepository repository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //-- business logic --//
        // parameter 변수에 저장
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        // db 에 저장
        Member member = new Member(username, age);
        repository.save(member);

        //-- Model 에 data 저장과 View 호출 로직 --//
        // jsp 에 넘겨줄 객체를 입력 (입력된 객체는 model 에 저장된다.)
        request.setAttribute("member", member);

        // forward 로 jsp 호출
        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
