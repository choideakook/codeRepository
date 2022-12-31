package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


// interface 에서 상속은 implements 가 아닌 extends 로 한다.
public interface SpringDataJpaMemberRepository
        // jpa repository 를 상속한 후 두번째 제네릭에 pk 의 데이터타입을 넣고
        // 두번째 parameter 에 Member repository 를 넣는다.
        extends JpaRepository<Member,Long>,MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
