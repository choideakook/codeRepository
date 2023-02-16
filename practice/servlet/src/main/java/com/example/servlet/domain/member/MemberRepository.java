package com.example.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어있지 않음 , 실무에서는 ConcurrentHashMap, AtomicLong 등 사용을 고려해야 한다.
 */
public class MemberRepository {

    // 사실 하나만 존재시키기 위해 싱글톤으로 설정했기 때문에 static 이 없어도 된다.
    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    // 싱글톤으로 운영하기 위해 instance 생성
    private static final MemberRepository instance = new MemberRepository();

    // Class 를 호출할 때 사용되는 method
    // 오직 이 method 를 통해서만 사용할 수 있음
    public static MemberRepository getInstance() {
        return instance;
    }

    // 싱글톤은 생성자를 생성할 수 없도록 private 으로 막아주어야 함
    private MemberRepository() {}

    //-- CURD logic --//

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
