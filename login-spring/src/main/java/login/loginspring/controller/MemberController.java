package login.loginspring.controller;

import login.loginspring.domain.Member;
import login.loginspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.*;

@Controller
public class MemberController {

    private final MemberService service;

    @Autowired
    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping ("/member/login")
    public String loginForm (MemberForm form){
        Member member = new Member();
        member.setUserid(form.getUserid());



        return "/member/loginForm";
    }

    @PostMapping ("/member/create")
    public String createForm (){
        return "/member/createForm";
    }

    @PostMapping ("/member/submit")
    public String submitForm (MemberForm form){
        Member member = new Member();
        member.setUserid(form.getUserid());

        service.join(member);
        return "redirect:/";
    }
}
