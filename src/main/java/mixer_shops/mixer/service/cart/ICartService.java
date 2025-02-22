package mixer_shops.mixer.service.cart;

import mixer_shops.mixer.dto.CartDto;

public interface ICartService {
	CartDto getCart(Long cartId);
	int getTotalPrice(Long cartId);
	void clearCart(Long cartId);
}
