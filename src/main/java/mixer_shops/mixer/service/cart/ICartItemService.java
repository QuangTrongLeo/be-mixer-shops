package mixer_shops.mixer.service.cart;

import mixer_shops.mixer.model.CartItem;

public interface ICartItemService {
	void addItemToCart(Long cartId, Long productId, Long colorId, Long sizeId, int quantity);
	void updateItemQuantity(Long cartId, Long productId, Long colorId, Long sizeId, int quantity);
	void removeItemToCart(Long cartId, Long productId, Long colorId, Long sizeId);
	CartItem getCartItem(Long cartId, Long productId, Long colorId, Long sizeId);
}
