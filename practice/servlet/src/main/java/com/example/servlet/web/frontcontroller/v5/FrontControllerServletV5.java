package com.example.servlet.web.frontcontroller.v5;

import com.example.servlet.web.frontcontroller.ModelView;
import com.example.servlet.web.frontcontroller.MyView;
import com.example.servlet.web.frontcontroller.v3.ControllerV3;
import com.example.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import com.example.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import com.example.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import com.example.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import com.example.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import com.example.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import com.example.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import com.example.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import org.apache.coyote.Adapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    // 기존의 매핑을 위한 Map 의 value 값은 특정 Controller 의 interface 였지만,
    // handler 의 value 값은 무엇이든 담을 수 있는 Object 타입이다.
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    // 여러개의 어댑터가 존재하기 때문에 어댑터를 보관할 수 있는 List 생성
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        // 요청 url 을 매핑해 Controller 구현체를 찾기위한 로직
        initHandlerMappingMap();
        // 어댑터를 초기화 하기위한 로직
        initHandlerAdapters();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 요청 URL 을  handler Mapping Map 으로 매핑할 수 있는 Controller (handler) 를 생성해줌
        Object handler = getHandler(request);
        // mapping 할 수 있는 url 이 없을경우 404 not found 출력
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 획득한 handler 가 상속하고있는 interface 를 찾는 로직
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        // 획득한 interface 와 구현 controller (handler) 를 실행한다.
        // business logic 을 완료하고 return 값인 model view 를 반환한다.
        ModelView mv = adapter.handle(request, response, handler);

        // model view 에서 논리 이름을 추출해 리졸버를 통해 완전한 경로로 완성해줌
        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        // JSP 파일을 호출해 HTML 을 랜더링한다.
        view.render(mv.getModel(), request, response);
    }


    // url 에 맞는 Controller 를 map 에 보관
    private void initHandlerMappingMap() {
        // V3
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        // V4
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }
    // Controller 에 맞는 어댑터를 List 에 보관
    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter()); // V3 어댑터
        handlerAdapters.add(new ControllerV4HandlerAdapter());// V4 어댑터
    }
    // 요청 URL 을  handler Mapping Map 으로 매핑할 수 있는 Controller 를 반환하는 method
    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        Object handler = handlerMappingMap.get(requestURI);
        return handler;
    }


    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        MyHandlerAdapter handlerAdapter;
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                handlerAdapter = adapter;
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter 를 찾을 수 없습니다.");
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
