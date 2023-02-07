package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.NoSuchElementException;

/**
 * JDBC - DriverManager 를 사용하기
 */
@Slf4j
public class MemberRepositoryV0 {

    // 실패할경우 발생할 error 를 밖으로 던짐
    public Member save(Member member) throws SQLException {
        // 필요한 필드들을 생성
        String sql = "insert into member(member_id, money) values(?, ?)";
        Connection con = null;
        PreparedStatement pstmt = null;

        // DB 와 연결하고 data 를 추가
        try {
            // Connection 획득 (DB 연결)
            con = getConnection();
            // Query 생성
            pstmt = con.prepareStatement(sql);
            // sql 의 values 의 parameter 순서를 적고 그 순서에 들어갈 값을 작성하면됨
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            // SQL 실행
            pstmt.executeUpdate();
            return member;
        } catch (SQLException e) {
            // 예외가 발생할 경우 에러대신 로그를 남김
            log.error("db error", e);
            // error 는 밖으로 던진다.
            throw e;
        }finally {
            // 마지막으로 Connection 을 닫아준다.
            close(con, pstmt, null);
        }
    }

    public Member findById(String memberId) throws SQLException {
        //리소스 생성
        String sql = "select * from member where member_id = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Connection 획득, SQL 생성
            con = DBConnectionUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            // data 조회, rs 에 결과 담기
            rs = pstmt.executeQuery();

            // rs 결과 확인
            if (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else {
                // 예외 throw
                throw new NoSuchElementException("member not found memberId = " + memberId);
            }

        } catch (SQLException e) {
            // 예외시 로그 남기고 에러 밖으로 throw
            log.error("db error", e);
            throw e;
        }finally {
            // Connection close
            close(con, pstmt, rs);
        }
    }

    public void update(String memberId, int money) throws SQLException {
        // 필드 생성
        String sql = "update member set money=? where member_id=?";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // Connection, SQL
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);

            // Query 를 실행하고 영향 받은 row 의 수를 출력하는 로직
            // id 를 특정했기 때문에 1 또는 0 이 출력될 수 밖에 없다.
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize={}", resultSize);

        } catch (SQLException e) {
            // error log
            log.error("db error", e);
            throw e;
        }finally {
            // connection close
            close(con, pstmt, null);
        }
    }

    public void delete(String memberId) throws SQLException {
        String sql = "delete from member where member_id=?";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, null);
        }

    }

    private void close(Connection con, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }

    }

    private static Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
