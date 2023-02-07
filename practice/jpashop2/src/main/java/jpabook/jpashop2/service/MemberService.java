package jpabook.jpashop2.service;

import jpabook.jpashop2.domain.Member;
import jpabook.jpashop2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member.getName());
        repository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(String name) {
        if (!repository.findByName(name).isEmpty()) {
            throw new IllegalStateException("this name has already been registered");
        }
    }

    public List<Member> findAllMember() {
        return repository.findAll();
    }

    public Member findOneMember(Long id) {
        return repository.findOne(id);
    }

}
