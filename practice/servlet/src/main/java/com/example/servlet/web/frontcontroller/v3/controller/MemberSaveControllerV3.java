package com.example.servlet.web.frontcontroller.v3.controller;

import com.example.servlet.domain.member.Member;
import com.example.servlet.domain.member.MemberRepository;
import com.example.servlet.web.frontcontroller.ModelView;
import com.example.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository repository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {

        // map 에서 클라이언트가 요청한 parameter 값을 변수에 저장
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // 클라이언트 요청정보로 db 에 저장
        Member member = new Member(username, age);
        repository.save(member);

        // model view 객체를 생성해 논리 이름을 넣어준다.
        ModelView mv = new ModelView("save-result");
        // model (map) 에 view 로 넘겨줄 data 를 입력한다.
        mv.getModel().put("member", member);

        // 완성된 model view 를 반환한다.
        return mv;
    }

}
