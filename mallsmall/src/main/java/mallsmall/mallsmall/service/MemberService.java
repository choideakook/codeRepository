package mallsmall.mallsmall.service;

import lombok.RequiredArgsConstructor;
import mallsmall.mallsmall.domain.Member;
import mallsmall.mallsmall.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    // sign up
    @Transactional
    public Long joinMember(Member member) {
        duplicateChecker(member.getName());
        repository.save(member);
        return member.getId();
    }

    // name duplicate checker
    public void duplicateChecker(String name) {
        if (!repository.findByName(name).isEmpty()) {
            throw new IllegalStateException(
                    "이미 등록된 이름입니다."
            );
        }
    }

    // find all member
    public List<Member> findAll() {
        return repository.findAll();
    }

    // find member
    public Member findOne(Long id) {
        return repository.findOne(id);
    }

    @Transactional
    // update member
    public void updateMember(Long id, String name) {
        Member findMember = repository.findOne(id);
        findMember.updateName(name);
    }
}
