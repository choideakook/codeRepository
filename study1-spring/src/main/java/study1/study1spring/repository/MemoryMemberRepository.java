package study1.study1spring.repository;

import study1.study1spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

            // db 의 역할을 할 Map 을 생성
            // key : id / value : name
    private static Map<Long, Member> store = new HashMap<>();
            // 회원 id 를 생성하기위해 key 값을 자동으로 생성하게 함
    private static Long sequence = 0L;

    @Override       // 정보 저장 method
    public Member save(Member member) {
            // sequence 에 1을 추가해서 id 에 넣어줌
        member.setId(++sequence);
            // id 를 get 을 통해 넣어뒀던 id 값을 불러와 name과 함께 map 에 넣어줌
        store.put(member.getId(), member);
            // 저장된 결과를 반환해줌
        return member;
    }

    @Override   // id 찾기 method
    public Optional<Member> findById(Long id) {
            // 인자값을 map 에서 get 으로 찾아봄
            // 결과가 없을때 null 을 반환하는걸 방지해서
            // obtional 로 방지함 -> 클라이언트에서 후조치 가능
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
                // value 값을 steam 하고
        return store.values().stream()
                    // filter 로 paramter 값과 일치하는 name 을 찾을경우
                .filter(Member -> Member.getName().equals(name))
                    // 찾은 값을 return 함 (못찾을 경우 null 반환)
                .findAny();
    }

    @Override
    public List<Member> findAll() {
                // 새로운 array list 를 생성해 value 값들을 리턴함
        return new ArrayList<>(store.values());
    }
    public void clearStore(){
        store.clear();
    }
}
