package itemservicedb.hello.service;

import itemservicedb.hello.domain.Item;
import itemservicedb.hello.repository.ItemSearchCond;
import itemservicedb.hello.repository.ItemUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item save(Item item);
    void update(Long itemId, ItemUpdateDto updateParam);
    Optional<Item> findById(Long id);
    List<Item> findItems(ItemSearchCond itemSearch);
}
