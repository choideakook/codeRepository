package jpabook.jpashop2.service;

import jpabook.jpashop2.domain.item.Item;
import jpabook.jpashop2.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    public void createItem(Item item) {
        repository.save(item);
    }

    @Transactional(readOnly = true)
    public List<Item> findItems() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Item findOne(Long id) {
        return repository.findOne(id);
    }

    public Long updateItem(Long id, String name, int price, int stockQuantity) {
        Item findItem = repository.findOne(id);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
        return findItem.getId();
    }

}
