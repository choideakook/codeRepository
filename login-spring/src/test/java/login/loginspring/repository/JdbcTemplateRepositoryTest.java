package login.loginspring.repository;

import login.loginspring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JdbcTemplateRepositoryTest {

    @Autowired MemberRepository repository;

    @Test
    void save() {
        Member member = new Member();
        member.setUserid("shdrnrhd11");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    void findById() {
    }

    @Test
    void findByUserId() {
    }

    @Test
    void findAll() {
    }
}