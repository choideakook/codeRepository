package com.example.servlet.web.frontcontroller.v3.controller;

import com.example.servlet.web.frontcontroller.ModelView;
import com.example.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {

        // return 값으로 jsp 파일경로의 논리 이름을 넘겨준다.
        return new ModelView("new-form");
    }
}
