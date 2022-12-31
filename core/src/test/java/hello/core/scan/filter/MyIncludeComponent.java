package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // type = class 레벨
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {

}
