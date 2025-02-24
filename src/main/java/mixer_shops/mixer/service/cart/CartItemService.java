package mixer_shops.mixer.service.cart;

import org.springframework.stereotype.Service;

import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
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
	@Transactional
	public void addItemToCart(Long cartId, Long productId, int quantity) {
	    try {
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
		} catch (OptimisticLockException e) {
			// TODO: handle exception
			throw new ResourcesException("Optimistic Locking failure occurred! Please try again.");
		}
	}

	@Override
	public void updateItemQuantity(Long cartId, Long productId, int quantity) {
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
		CartItem cartItem = getCartItem(cartId, productId);
		cart.removeItem(cartItem);
		cartRepository.save(cart);
	}
	
	@Override
	public CartItem getCartItem(Long cartId, Long productId) {
		Cart cart = cartRepository.findById(cartId)
	            .orElseThrow(() -> new ResourcesException("Cart not found!"));
		CartItem cartItem = cart.getCartItems().stream().filter(item -> item.getProduct().getId().equals(productId))
				.findFirst().orElseThrow(() -> new ResourcesException("Item not found!"));
		return cartItem;
	}

}
