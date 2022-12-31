package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements DiscountPolicy{

    // VIP 대상 1,000 원 할인
    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        // Enum 형태 비교는 equal 이 아닌 == 으로 사용해야 한다.
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
