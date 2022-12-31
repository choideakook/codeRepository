package hello.core.discount;

import hello.core.AutoAppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class NoUniqueBeanDefinitionExceptionTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    @Test
    void duplicatedBean () {

        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);

        for (String key : beansOfType.keySet()) {
            System.out.println("s = " + key);
        }


    }
}
