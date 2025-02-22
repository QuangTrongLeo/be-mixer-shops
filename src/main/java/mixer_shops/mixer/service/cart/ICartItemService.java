package mixer_shops.mixer.service.cart;

public interface ICartItemService {
	void addItemToCart(Long cartId, Long productId, int quantity);
	void updateItemToCart(Long cartId, Long productId, int quantity);
	void removeItemToCart(Long cartId, Long productId);
}
