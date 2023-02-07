package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;

/**
 * Transaction - Transaction Manager
 */
@RequiredArgsConstructor
@Slf4j
public class MemberServiceV3_1 {

    // Data Source 의존관계를 없앤다.
    // private final DataSource dataSource;

    // Transaction Manager 를 의존시킨다.
    private final PlatformTransactionManager transactionManager;
    private final MemberRepositoryV3 memberRepository;

    // Business logic
    private void buzLogic(String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);

        int fromMemberMoney = fromMember.getMoney();
        int toMemberMoney = toMember.getMoney();

        if (fromMemberMoney - money < 0) throw new IllegalStateException("잔액이 부족합니다.");

        memberRepository.update(fromId, fromMemberMoney - money);

        if (toId.equals("ex")) throw new IllegalStateException("이체중 예외 발생");

        memberRepository.update(toId, toMemberMoney + money);
    }

    // Transaction Logic
    public void accountTransaction(String fromId, String toId, int money) throws SQLException {

        // Transaction 시작
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            // Business logic
            buzLogic(fromId, toId, money);

            // Transaction 성공 종료 - commit
            transactionManager.commit(status);

        } catch (Exception e) {
            // Transaction 실패 종료 - rollback
            transactionManager.rollback(status);
            throw new IllegalStateException(e);
        }

    }
}
