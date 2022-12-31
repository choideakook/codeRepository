package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    /**
     * Jdbc 기반 DB 연결시 사용
     */
//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    /**
     * JPA 기반 DB 연결시 사용
     */
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    /**
     * Spring data JPA 연결시 사용
     */
    private final MemberRepository memberRepository;
    // 생성자가 한가지 밖에 없을경우 Auto wired 생략 가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){

        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository(){

//        return new MemoryMemberRepository();
//        return new JdbcMemberRepositoy(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepositoy(em);
//    }

}
