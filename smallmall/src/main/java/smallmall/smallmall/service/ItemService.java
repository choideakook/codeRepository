package smallmall.smallmall.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smallmall.smallmall.domain.item.Item;
import smallmall.smallmall.repository.ItemRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    // item 등록
    public Long create(Item item) {
        validateDuplicate(item);
        repository.save(item);
        return item.getId();
    }

    // item 이름 중복확인
    @Transactional(readOnly = true)
    private void validateDuplicate(Item item) {
        List<Item> findItems = repository.findByName(item.getName());
        if (!findItems.isEmpty()) {
            throw new IllegalStateException("this Item has already been registered");
        }
    }

    // item 이름 수정
    public void updateName(Long id, String name) {
        Item findItem = repository.findOne(id);
        findItem.setName(name);
    }

}