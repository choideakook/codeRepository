package com.example.servlet.web.frontcontroller.v2;

import com.example.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

    // V1 과 같지만 return 값으로 My view 를 return 할 수 있게 수정해준다.
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
