package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional (readOnly = true) // readonly true 는 DB 변경이아닌 읽기 전용이라는 의미
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional // join 은 DB 변경이 가능하기때문에 True 를 해주면 안된다 (default = false)
    public Long join(Member member) {

        validateDuplicateMember (member);
        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {

        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    // 조회 기능이기때문에 Transactional 읽기 전용에 해당한다.
    public List<Member> findMembers (){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
