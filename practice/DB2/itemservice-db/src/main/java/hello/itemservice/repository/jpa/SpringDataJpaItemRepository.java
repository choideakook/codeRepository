package hello.itemservice.repository.jpa;

import hello.itemservice.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// 제네릭에 Entity 를 입력해 주는것 만으로도 기본적인 CRUD 는 Method 없이 사용할 수 있다.
public interface SpringDataJpaItemRepository extends JpaRepository<Item, Long> {

    // 상품 이름으로 Item 조회
    // like 를 사용해 parameter 가 포함된 ItemName 까지 조회
    List<Item> findByItemNameLike(String itemName);

    // 가격으로 Item 조회
    // Less Than 과 Equal 을 사용해 parameter 보다 작거나 같은 값을 조회
    List<Item> findByPriceLessThanEqual(Integer price);

    /**
     * 위의 두가지 Method 를 하나로 합친 method
     */

    // 방법 1. Query Method
    // 단순하게 필요한 내용을 줄줄 작성하면 된다.
    List<Item> findByItemNameLikeAndPriceLessThanEqual(String itemName, Integer price);

    // 방법 2. Query 직접 실행
    // @Query 에 JPQL 문을 작성하면 된다.
    @Query("select i from Item i where i.itemName like :itemName and i.price <= :price")
    List<Item> findItems(@Param("itemName") String itemName, @Param("price") Integer price);
}
