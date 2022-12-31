package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

@Configuration
@ComponentScan(
        basePackageClasses = AutoAppConfig.class,
                excludeFilters = @Filter(
                        type = FilterType.ANNOTATION, classes = Configuration.class
        )

)
public class AutoAppConfig {

}
