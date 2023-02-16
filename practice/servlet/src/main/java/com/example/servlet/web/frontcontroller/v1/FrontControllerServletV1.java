package com.example.servlet.web.frontcontroller.v1;

import com.example.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import com.example.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import com.example.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// url 경로에 * 을 입력하면 해당 경로로 요청되는 모든 url 을 매핑할 수 있다.
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    // Mapping 정보를 보관하는 Map 생성
    // key : url
    // value : Controller
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    // http 요청이 오면 url 을 map 에 put 해서 Controller 를 get 할 수 있다.
    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // front controller 가 제대로 작동하는지 확인하기 위한 출력 로직
        System.out.println("FrontControllerServletV1.service");

        // http 요청의 url 을 변수에 저장
        String requestURI = request.getRequestURI();
        // uri 를 통해 map 의 구현체를 변수에 저장
        ControllerV1 controller = controllerMap.get(requestURI);
        // 만약 해당 url 이 없을경우 404 not found 응답
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // map 에서 찾은 구현체를 실행시킨다.
        controller.process(request, response);
    }
}
