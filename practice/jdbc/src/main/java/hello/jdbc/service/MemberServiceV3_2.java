package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.SQLException;

/**
 * Transaction - Transaction Template
 */
@Slf4j
public class MemberServiceV3_2 {

    // private final PlatformTransactionManager transactionManager;

    private final TransactionTemplate txTemplate;
    private final MemberRepositoryV3 memberRepository;

    public MemberServiceV3_2(PlatformTransactionManager transactionManager, MemberRepositoryV3 memberRepository) {
        this.txTemplate = new TransactionTemplate(transactionManager);
        this.memberRepository = memberRepository;
    }

    // Transaction Logic
    public void accountTransaction(String fromId, String toId, int money) throws SQLException {

        txTemplate.executeWithoutResult(status ->{
            // Business logic
            try {
                buzLogic(fromId, toId, money);
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

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
}
