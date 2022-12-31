package study1.study1spring.repository;
import study1.study1spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save (Member member);
    // 옵셔널 안의 매소드들을 활용하기 위해 데이터 타입을 optional로 지정
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
