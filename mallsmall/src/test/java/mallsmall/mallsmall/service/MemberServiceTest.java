package mallsmall.mallsmall.service;

import mallsmall.mallsmall.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;

    @Test
    void save_member() {
        Member member = new Member("A", "seoul", "dul-le");

        Long memberId = memberService.joinMember(member);

        Member findMember = memberService.findOne(memberId);
        assertThat(member.getId()).isEqualTo(memberId);
        assertThat(findMember.getName()).isEqualTo("A");
        assertThat(findMember.getAddress()).isSameAs(member.getAddress());
    }

    @Test
    void name_Duplicate() {
        Member member1 = new Member("A", "seoul", "dul-le");
        Member member2 = new Member("A", null, null);

        memberService.joinMember(member1);

        IllegalStateException e = assertThrows
                (IllegalStateException.class, () -> memberService.joinMember(member2));
    }
}