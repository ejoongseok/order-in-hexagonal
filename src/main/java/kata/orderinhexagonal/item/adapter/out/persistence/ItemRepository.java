package kata.orderinhexagonal.item.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
