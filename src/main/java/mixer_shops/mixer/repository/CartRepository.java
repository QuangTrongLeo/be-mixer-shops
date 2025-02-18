package mixer_shops.mixer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mixer_shops.mixer.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
