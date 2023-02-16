package com.example.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Spring Bean 에 등록하기 위해 @Component 어노테이션을 사용한다.
// Spring Bean 의 이름을 지정하기 위해 괄호에 직접 작성해준다.
@Component("/springmvc/old-controller")
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }
}
