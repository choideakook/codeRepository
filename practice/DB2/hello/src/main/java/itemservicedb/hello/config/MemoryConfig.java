package itemservicedb.hello.config;

import itemservicedb.hello.repository.ItemRepository;
import itemservicedb.hello.repository.memory.MemoryItemRepository;
import itemservicedb.hello.service.ItemService;
import itemservicedb.hello.service.ItemServiceV1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemoryConfig {

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }
    @Bean
    public ItemRepository itemRepository() {
        return new MemoryItemRepository();
    }
}
