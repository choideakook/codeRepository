package login.loginspring.repository;

import login.loginspring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JdbcTemplateRepository implements MemberRepository{

    private final JdbcTemplate jtmp;

    @Autowired
    public JdbcTemplateRepository(DataSource source){
        jtmp = new JdbcTemplate(source);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jtmp);
        insert.withTableName("member")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> params = new HashMap<>();
        params.put("userid", member.getUserid());

        Number key = insert.executeAndReturnKey(
                new MapSqlParameterSource(params)
        );
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jtmp.query(
                "select * from member where id = ?", rowMapper(), id
        );
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByUserId(String userid) {
        List<Member> result = jtmp.query(
                "select * from member where userid = ?", rowMapper(), userid
        );
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jtmp.query("select * from member", rowMapper());
    }

    private RowMapper<Member> rowMapper(){
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setUserid(rs.getString("userid"));
            return member;
        };
    }
}
