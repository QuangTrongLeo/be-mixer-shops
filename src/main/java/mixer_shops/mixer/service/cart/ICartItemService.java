package mixer_shops.mixer.service.cart;

import mixer_shops.mixer.model.CartItem;

public interface ICartItemService {
	void addItemToCart(Long cartId, Long productId, int quantity);
	void updateItemQuantity(Long cartId, Long productId, int quantity);
	void removeItemToCart(Long cartId, Long productId);
	CartItem getCartItem(Long cartId, Long productId);
}
