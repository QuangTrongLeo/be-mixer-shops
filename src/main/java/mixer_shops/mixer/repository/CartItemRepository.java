package mixer_shops.mixer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mixer_shops.mixer.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	void deleteAllByCartId(Long cartId);

}
