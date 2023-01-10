package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    // persist -> 영속성 Context 에 member entity 객체를 넣음
    // 트렌젝션이 commit 되는 순간 DB 에 반영됨
    public void save(Member member) {
        em.persist(member);
    }

    // 단건 조회 (첫번째 - 타입 , 두번째 - PK)
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // JPQL (=쿼리문사용) 은 SQL 문법과 비슷하지만 대상이 Table 이 아닌 Entity 객체 이다.
    public List<Member> findAll (){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 파라미터 바인딩을 사용해 특정 정보를 찾는 방법
    public List<Member> findByName (String name){
        return em.createQuery(
                        "select m from Member m where m.name = :name", Member.class
                ).setParameter("name", name)
                .getResultList();
    }
}
