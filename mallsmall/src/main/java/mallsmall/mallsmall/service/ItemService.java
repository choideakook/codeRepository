package mallsmall.mallsmall.service;

import lombok.RequiredArgsConstructor;
import mallsmall.mallsmall.domain.item.Book;
import mallsmall.mallsmall.domain.item.Item;
import mallsmall.mallsmall.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    // create item
    @Transactional
    public Long saveItem(Item item) {
        duplicateChecker(item.getName());
        repository.save(item);
        return item.getId();
    }

    // item's name duplicate
    private void duplicateChecker(String name) {
        if (!repository.findByName(name).isEmpty()) {
            throw new IllegalStateException("이미 등록된 상품입니다.");
        }
    }

    // find All item
    public List<Item> findAll() {
        return repository.findAll();
    }

    // update
    @Transactional
    public void update(Long id, String name, int price, int stockQuantity) {
        duplicateChecker(name);
        Item findItem = repository.findOne(id);
        findItem.updateItem(name, price, stockQuantity);
    }

    // find item
    public Item findOne(Long id) {
        return repository.findOne(id);
    }
}
