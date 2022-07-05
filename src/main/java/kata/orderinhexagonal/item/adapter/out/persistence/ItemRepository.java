package kata.orderinhexagonal.item.adapter.out.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
	@Query("select i from ItemEntity i left join fetch i.discount where i.id = ?1")
	Optional<ItemEntity> findItemAndDiscountFetchById(Long id);
}
