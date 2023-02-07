package itemservicedb.hello.config;

import itemservicedb.hello.repository.ItemRepository;
import itemservicedb.hello.repository.jdbcTemplate.JdbcTemplateItemRepositoryV1;
import itemservicedb.hello.service.ItemService;
import itemservicedb.hello.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class JdbcTemplateV1Config {

    private final DataSource dataSource;

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository() {
        return new JdbcTemplateItemRepositoryV1(dataSource);

    }
}
