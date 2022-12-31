package login.loginspring.service;

import login.loginspring.domain.Member;
import login.loginspring.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService service;
    @Autowired MemberRepository repository;

    @Test
    void join() {
        Member member = new Member();
        member.setUserid("shdrnrhd11");

        Long saveId = service.join(member);

        Member result = service.findOne(saveId).get();
        assertThat(member.getUserid()).isEqualTo(result.getUserid());
    }
}