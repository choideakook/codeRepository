package com.example.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyView {

    private String viewPath;

    // view path 필드를 외부에서 생성자 주입 방식으로 값을 주입한다.
    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    // view path 를 통해 JSP 파일을 호출하는 로직
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    // map 에 담긴 request 값을 model 에 전달하는 기능을 포함한 로직
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // business 로직을 완료한후 Controller 가 map 에 담은 data (model) 를
        // setAttribute 메서드로 Model 객체에 담아준다.
        modelToRequestAttribute(model, request);

        //view path 를 통해 JSP 파일을 호출한다.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    private static void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
