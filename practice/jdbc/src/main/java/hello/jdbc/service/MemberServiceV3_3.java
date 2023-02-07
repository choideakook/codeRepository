package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * Transaction - Transaction AOP
 */
@Slf4j
public class MemberServiceV3_3 {

    private final MemberRepositoryV3 memberRepository;

    public MemberServiceV3_3(MemberRepositoryV3 memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Transaction Logic
    @Transactional
    public void accountTransaction(String fromId, String toId, int money) throws SQLException {
        buzLogic(fromId, toId, money);
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
