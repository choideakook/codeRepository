package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
//    @Rollback (value = false)
    void 회원가입() {
        Member member = new Member();
        member.setName("Kim");

        Long join = memberService.join(member);
        Member memberId = memberService.findOne(member.getId());

        assertThat(member.getId()).isEqualTo(join);
        assertThat(member.getId()).isEqualTo(memberId.getId());
        assertThat(member).isSameAs(memberRepository.findOne(join));
    }

    @Test
    void 중복_회원_예외(){

        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("member1");
        member2.setName("member1");

        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));
    }
}