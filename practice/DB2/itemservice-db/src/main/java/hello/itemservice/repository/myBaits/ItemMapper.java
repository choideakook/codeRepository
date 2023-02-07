package hello.itemservice.repository.myBaits;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    // parameter 가 하나지만 item 의 필드값을 모두 사용할 수 있다.
    void save(Item item);

    // parameter 가 2개 이상일 경우 @Param 어노테이션을 붙여주어야 한다.
    void update(@Param("id") Long id, @Param("updateParam") ItemUpdateDto updateParam);

    Optional<Item> findById(Long id);

    List<Item> findAll(ItemSearchCond itemSearchCond);
}
