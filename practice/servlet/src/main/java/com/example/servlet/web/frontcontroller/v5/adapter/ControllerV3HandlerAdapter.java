package com.example.servlet.web.frontcontroller.v5.adapter;

import com.example.servlet.web.frontcontroller.ModelView;
import com.example.servlet.web.frontcontroller.v3.ControllerV3;
import com.example.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        //요청한 구현체가 V3 를 상속하는지 확인하는 로직
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        //Boolean 으로 판별이 된 Object 로 로직을 수행한다.
        // Controller V3 로 type 을 캐스팅 한다.
        ControllerV3 controller = (ControllerV3) handler;

        // 요청 parameter 를 map 으로 변환
        Map<String, String> paramMap = createParamMap(request);
        // 변환된 map 을 통해 Controller 를 실행시킨다.
        // Controller 의 business 로직의 결과와 논리 이름을 return 값으로 받는다.
        ModelView mv = controller.process(paramMap);
        //반환된 return 값을 반환한다.
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
