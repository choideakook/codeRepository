package jpabook.jpashop2.service;

import jpabook.jpashop2.domain.Address;
import jpabook.jpashop2.domain.Member;
import jpabook.jpashop2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired private MemberService service;
    @Autowired private MemberRepository repository;
    @Autowired private EntityManager em;

    @Test
    void 회원가입() {
        Member member = new Member();
        member.setName("A");
        member.setAddress(new Address("c", "s", "z"));

        Long memberId = service.join(member);
        Member findMember = service.findOneMember(memberId);

        em.flush();
        assertThat(findMember).isEqualTo(member);
        assertThat(findMember).isEqualTo(repository.findOne(memberId));
        assertThat(findMember.getAddress()).isEqualTo(member.getAddress());
        assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void 중복예외() {
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("A");
        member2.setName("A");

        Long member1Id = service.join(member1);
        Member findMember1 = service.findOneMember(member1Id);
        assertThat(findMember1).isEqualTo(member1);

        assertThatThrownBy(() -> service.join(member2))
                .isInstanceOf(IllegalStateException.class);
    }
}