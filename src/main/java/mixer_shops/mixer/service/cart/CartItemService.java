package mixer_shops.mixer.service.cart;

import org.springframework.stereotype.Service;

import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.model.Cart;
import mixer_shops.mixer.model.CartItem;
import mixer_shops.mixer.model.Product;
import mixer_shops.mixer.repository.CartItemRepository;
import mixer_shops.mixer.repository.CartRepository;
import mixer_shops.mixer.repository.ProductRepository;

@Service
public class CartItemService implements ICartItemService{
	private final CartItemRepository cartItemRepository;
	private final ProductRepository productRepository;
	private final CartRepository cartRepository;

	public CartItemService(CartItemRepository cartItemRepository, ProductRepository productRepository,
			CartRepository cartRepository) {
		super();
		this.cartItemRepository = cartItemRepository;
		this.productRepository = productRepository;
		this.cartRepository = cartRepository;
	}


	@Override
	public void addItemToCart(Long cartId, Long productId, int quantity) {
	    Cart cart = cartRepository.findById(cartId)
	            .orElseThrow(() -> new ResourcesException("Cart not found!"));
	    Product product = productRepository.findById(productId)
	            .orElseThrow(() -> new ResourcesException("Product not found!"));

	    CartItem cartItem = cart.getCartItems()
	            .stream()
	            .filter(item -> item.getProduct().getId().equals(productId))
	            .findFirst().orElse(null);

	    if (cartItem == null) {
	        cartItem = new CartItem();
	        cartItem.setCart(cart);
	        cartItem.setProduct(product);
	        cartItem.setQuantity(quantity);
	        cartItem.setPrice(product.getPrice());
	        cart.addItem(cartItem);
	    } else {
	        cartItem.setQuantity(cartItem.getQuantity() + quantity);
	    }

	    // Cập nhật lại tổng giá
	    cartItem.setTotalPrice(cartItem.getPrice() * cartItem.getQuantity());
	    
	    cartItemRepository.save(cartItem);
	    cartRepository.save(cart);
	}


	@Override
	public void updateItemToCart(Long cartId, Long productId, int quantity) {
		// TODO Auto-generated method stub
		Cart cart = cartRepository.findById(cartId)
	            .orElseThrow(() -> new ResourcesException("Cart not found!"));
		cart.getCartItems().stream()
				.filter(item -> item.getProduct().getId().equals(productId))
				.findFirst()
				.ifPresent(item -> {
					item.setQuantity(quantity);
					item.setPrice(item.getProduct().getPrice());
					item.setTotalPrice(item.getPrice() * item.getQuantity());
				});
		int totalAmount = cart.getTotalAmount();
		cart.setTotalAmount(totalAmount);
		cartRepository.save(cart);
	}

	@Override
	public void removeItemToCart(Long cartId, Long productId) {
		// TODO Auto-generated method stub
		Cart cart = cartRepository.findById(cartId)
	            .orElseThrow(() -> new ResourcesException("Cart not found!"));
		CartItem cartItem = cart.getCartItems().stream()
				.filter(item -> item.getProduct().getId().equals(productId))
				.findFirst()
				.orElseThrow(() -> new ResourcesException("Product not found!"));
		cart.removeItem(cartItem);
		cartRepository.save(cart);
	}

}
