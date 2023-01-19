package smallmall.smallmall.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import smallmall.smallmall.domain.Address;
import smallmall.smallmall.domain.Member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService service;

    @Test
    void save_member() {
        Member member = new Member("MemberA");
        member.setAddress("010","seoul");

        Long createMember = service.join(member);

        Member findMember = service.findOne(createMember);

        assertThat(findMember.getName()).isEqualTo(member.getName());
        assertThat(findMember.getAddress().getPhone()).isEqualTo(member.getAddress().getPhone());
    }

    @Test
    void duplicate_name() {
        Member member1 = new Member("MemberA");
        Member member2 = new Member("MemberA");

        Long createMember = service.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> service.join(member2));
    }
}