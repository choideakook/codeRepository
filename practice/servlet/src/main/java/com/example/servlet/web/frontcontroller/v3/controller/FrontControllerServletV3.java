package com.example.servlet.web.frontcontroller.v3.controller;

import com.example.servlet.web.frontcontroller.ModelView;
import com.example.servlet.web.frontcontroller.MyView;
import com.example.servlet.web.frontcontroller.v3.ControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // HTTP 요청 param 값을 map 으로 변환하는 로직
        Map<String, String> paramMap = createParamMap(request);

        // 변환된 param 값을 담은 map 을 통해 process 를 실행한다.
        // 실행 결과의 반환값으로 model view 가 return 됨
        ModelView mv = controller.process(paramMap);

        // 논리이름을 완전한 경로로 만들어주기 위해 view name 만 추출한다.
        String viewName = mv.getViewName();
        // My view 를 생성해 논리이름을 완전한 경로로 만들어준다.
        MyView view = viewResolver(viewName);

        // 완성된 경로를 통해 JSP 파일을 실행시킨다.
        view.render(mv.getModel(), request, response);
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                        .forEachRemaining(paramName ->
                                paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
