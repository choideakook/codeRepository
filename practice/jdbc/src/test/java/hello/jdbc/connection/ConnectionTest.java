package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    // DriverManager 를 사용한 Connection 획득
    @Test
    void driverManager() throws SQLException {
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        log.info("Connection = {}, Class = {}", con1, con1.getClass());
        log.info("Connection = {}, Class = {}", con2, con2.getClass());
    }

    // dataSourceDriverManager 를 사용한 Connection 획득
    // 호출을 받으면 항상 새로운 Connection 을 획득함
    @Test
    void dataSourceDriverManager() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(dataSource);
    }

    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
        log.info("Connection = {}, Class = {}", con1, con1.getClass());
        log.info("Connection = {}, Class = {}", con2, con2.getClass());
    }

    // Connection Pooling (커넥션 풀을 사용한 커넥션 획득)
    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        // Connection 의 최대 갯수 설정
        dataSource.setMaximumPoolSize(10);
        // Pool 의 이름 명명
        dataSource.setPoolName("My Pool");

        useDataSource(dataSource);
        Thread.sleep(1000);
    }
}
