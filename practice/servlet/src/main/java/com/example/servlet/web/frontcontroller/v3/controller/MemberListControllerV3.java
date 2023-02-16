package com.example.servlet.web.frontcontroller.v3.controller;

import com.example.servlet.domain.member.Member;
import com.example.servlet.domain.member.MemberRepository;
import com.example.servlet.web.frontcontroller.ModelView;
import com.example.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository repository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {

        List<Member> members = repository.findAll();

        // model view 객체를 생성해 논리 이름을 넣어준다.
        ModelView mv = new ModelView("members");
        // model (map) 에 view 로 넘겨줄 data 를 입력한다.
        mv.getModel().put("members", members);

        // 완성된 model view 를 반환한다.
        return mv;
    }
}
