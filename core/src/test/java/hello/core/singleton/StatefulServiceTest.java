package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {


    @Test
    void statefulServiceSingleton (){

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자 10,000 원 주문
        int userA = statefulService1.order("UserA", 10000);
        // ThreadB : B사용자 20,000 원 주문
        int userB = statefulService2.order("UserB", 20000);

        // ThreadA : A사용자 주문 금액 조회
        int price = userA;
        System.out.println("price = " + price);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService (){
            return new StatefulService();
        }
    }

}