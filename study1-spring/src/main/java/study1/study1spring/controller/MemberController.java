package study1.study1spring.controller;

import org.springframework.stereotype.Controller;
import study1.study1spring.service.MemberService;

@Controller
public class MemberController {

    private MemberService memberService = new MemberService();
}
