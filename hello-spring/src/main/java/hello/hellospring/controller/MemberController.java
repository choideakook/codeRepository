package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

//    @Autowired       -> setter 주입 방식
//    public  void setMemberService (MemberService memberService){
//        this.memberService = memberService;
//    }

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 회원 가입 page 구현
     */
    @GetMapping("/members/new")
    public String createForm () {
        return "members/createMemberForm";
    }

    //@PostMapping : html form 태그가 보낸 값을 받아옴
    @PostMapping ("/members/new")
    public String create (MemberForm form){
        Member member = new Member ();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    /**
     * 회원 정보 출력 page 구현
     */
    @GetMapping ("/members")
    public String list (Model model){
        List <Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
