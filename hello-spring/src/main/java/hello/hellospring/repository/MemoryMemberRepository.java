package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;


    /**
     * 회원 정보 저장
     */
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    /**
     * 회원 id 찾기
     */
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    /**
     * 회원 name 찾기
     */
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    /**
     * 모든 회원 정보 조회
     */
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    /**
     * 모든 회원 정보 삭제
     */
    public void clearStore() {
        store.clear();
    }
}
