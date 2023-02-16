package com.example.servlet.web.frontcontroller.v3;

import com.example.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    // servlet 기술에 종속적이지 않은 method 를 생성
    ModelView process(Map<String, String> paramMap);
}
