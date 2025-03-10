package mixer_shops.mixer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mixer_shops.mixer.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findByUserId(Long userId);

}
