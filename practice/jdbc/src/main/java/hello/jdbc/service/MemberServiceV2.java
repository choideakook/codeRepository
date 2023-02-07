package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Transaction - param 연동, connection pool 을 고려한 종료
 */
@RequiredArgsConstructor
@Slf4j
public class MemberServiceV2 {

    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;

    // Business logic
    private void buzLogic(String fromId, String toId, int money, Connection con) throws SQLException {
        Member fromMember = memberRepository.findById(con, fromId);
        Member toMember = memberRepository.findById(con, toId);

        int fromMemberMoney = fromMember.getMoney();
        int toMemberMoney = toMember.getMoney();

        if (fromMemberMoney - money < 0) throw new IllegalStateException("잔액이 부족합니다.");

        memberRepository.update(con, fromId, fromMemberMoney - money);

        if (toId.equals("ex")) throw new IllegalStateException("이체중 예외 발생");

        memberRepository.update(con, toId, toMemberMoney + money);
    }

    // Transaction Logic
    public void accountTransaction(String fromId, String toId, int money) throws SQLException {

        Connection con = dataSource.getConnection();
        try {
            // Transaction 시작
            con.setAutoCommit(false);

            // Business logic
            buzLogic(fromId, toId, money, con);

            // Transaction 성공 종료
            con.commit();

        } catch (Exception e) {
            // Transaction 실패 종료
            con.rollback();
            throw new IllegalStateException(e);
        }finally {
            if (con != null) {
                try {
                    // 다음 사람을 위해 자동 Commit 으로 다시 바꿔줘야 한다.
                    con.setAutoCommit(true);
                    // Connection 종료
                    con.close();
                } catch (Exception e) {
                    log.info("error", e);
                }
            }
        }

    }
}
