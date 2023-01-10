package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping ("/members/new")
    public String createForm(Model model) {
        // addAttribute methode 를 사용해
        // Controller 에서 view 로 넘어갈 때
        // 괄호안의 데이터를 같이 넘겨줌
        // 괄호 첫번째는 Key 이고 두번째는 Value 이다.
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    // @Valid 은 member form 의 @NotEmpty 를 체크해서 누락되지 않게 확인해줌
    // BindingResult 는 @Valid 에 의한 error 발생시
    //  @NotEmpty 에 입력했던 message 를 가지고 error 를 무시하고 method 를 실행한다.
    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {

        // 만약 @Valid 에 의한 error 발생시 message 를 해당 page 에 return 해라
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        // 사용자가 입력한 값을 기반으로 회원 가입을 한 후 home 화면으로 돌아감
        Member member = new Member();
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        member.setName(form.getName());
        member.setAddress(address);
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) { // 모델을 통해 화면에 겍체를 전달하게 됨
        List<Member> members = memberService.findMembers();
        // model 에 members 값을 view 로 넘겨줌
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
