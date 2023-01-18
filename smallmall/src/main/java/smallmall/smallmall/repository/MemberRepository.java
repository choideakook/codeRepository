package smallmall.smallmall.repository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import smallmall.smallmall.domain.Address;
import smallmall.smallmall.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    // member 저장
    public void save(Member member) {
        em.persist(member);
    }

    // id 로 member 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // 전체 member 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // name 으로 member 조회
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name =:name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
