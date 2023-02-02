package itemservicedb.hello.repository;

import itemservicedb.hello.domain.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    Item save(Item item);
    void update(Long itemId, ItemUpdateDto updateParam);
    Optional<Item> findById(Long id);
    List<Item> findAll(ItemSearchCond cond);
}
