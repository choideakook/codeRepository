package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

/**
 * JDBC Template 사용
 */
@Slf4j
public class MemberRepositoryV5 implements MemberRepository {

    // 기존에 사용했던 의존관계들을 모두 JdbcTemplate 하나로 해결할 수 있다.
    private final JdbcTemplate template;

    public MemberRepositoryV5(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member){
        String sql = "insert into member(member_id, money) values(?, ?)";

        // sql 문을 첫번째 param 으로 하고 그 다음부터는 SQL 의 param 값을 넣어주면 된다.
        // template 이 커넥션 연결과 종료, 예외 변환, 예외 throw 등
        // JDBC 로 구연했던 모든 기능을 대신할 수 있다.
        template.update(sql, member.getMemberId(), member.getMoney());
        return member;
    }

    @Override
    public Member findById(String memberId){
        String sql = "select * from member where member_id = ?";

        // data 1 건을 조회하는 method
        return template.queryForObject(sql, memberRowMapper(), memberId);
    }

    // DB 의 응답 값을 RowMapper 가 받아서 return 값으로 변수에 입력해줌
    private RowMapper<Member> memberRowMapper() {
        // rs = result set (DB 의 응답 값)
        return (rs, rowNum)-> {
            Member member = new Member();
            member.setMemberId(rs.getString("member_id"));
            member.setMoney(rs.getInt("money"));
            return member;
        };
    }

    @Override
    public void update(String memberId, int money){
        String sql = "update member set money=? where member_id=?";
        // SQL parameter 순서를 잘 지켜서 입력해주어야 함
        template.update(sql, money, memberId);
    }

    @Override
    public void delete(String memberId){
        String sql = "delete from member where member_id=?";

        template.update(sql, memberId);
    }

    // Connection 동기화와 종료도 별도로 할 필요없이 Template 에서 전부 처리해준다.
}
