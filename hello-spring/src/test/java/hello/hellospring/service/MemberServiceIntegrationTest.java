package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository MemberRepository;

    @Test
    void 회원가입() {
        Member member = new Member();
        member.setName("Spring1");

        Long saveId = memberService.join(member);

        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getId()).isEqualTo(findMember.getId());
    }

    @Test
    public void 중복검사 (){
        Member member1 = new Member();
        member1.setName("Spring1");
        Member member2 = new Member();
        member2.setName("Spring1");

        memberService.join(member1);

        IllegalStateException e = assertThrows(
                IllegalStateException.class, () -> memberService.join(member2)
        );
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}