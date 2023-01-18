package smallmall.smallmall.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smallmall.smallmall.domain.Member;
import smallmall.smallmall.repository.MemberRepository;

import javax.persistence.Entity;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    // 회원 정보 저장
    @Transactional
    public Long join(Member member) {
        duplicateTest(member);
        repository.save(member);
        return member.getId();
    }

    // 회원 name 중복 체크
    private void duplicateTest(Member member) {
        List<Member> findMembers = repository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("this name has already been registered");
        }
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return repository.findAll();
    }

    // id 로 회원 조회
    public Member findOne(Long id) {
        return repository.findOne(id);
    }
}
