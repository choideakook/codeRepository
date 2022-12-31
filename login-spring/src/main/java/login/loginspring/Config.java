package login.loginspring;

import login.loginspring.domain.Member;
import login.loginspring.repository.JdbcTemplateRepository;
import login.loginspring.repository.MemberRepository;
import login.loginspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class Config {

    private DataSource source;

    @Autowired
    public Config(DataSource source) {
        this.source = source;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JdbcTemplateRepository(source);
    }
}
