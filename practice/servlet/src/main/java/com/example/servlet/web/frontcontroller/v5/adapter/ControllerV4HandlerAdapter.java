package com.example.servlet.web.frontcontroller.v5.adapter;

import com.example.servlet.web.frontcontroller.ModelView;
import com.example.servlet.web.frontcontroller.v4.ControllerV4;
import com.example.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        // 구현 Controller 객체를 부모 interface 타입으로 캐스팅
        ControllerV4 controller = (ControllerV4) handler;

        // 요청 param 값을 map 으로 변환
        Map<String, String> paramMap = createParamMap(request);
        // servlet 으로부터 독립시키기 위해 business 로직의 결과를 담을 map 생성
        HashMap<String, Object> model = new HashMap<>();

        // Controller 실행
        // 실행의 결과로 논리 이름 값을 반환한다.
        String viewName = controller.process(paramMap, model);

        // 논리 이름과 model 값을 입력한 Model View 객체 생성
        ModelView mv = new ModelView(viewName);
        mv.setModel(model);
        // 생성된 model view 객체를 반환한다.
        return mv;
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName ->
                        paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
