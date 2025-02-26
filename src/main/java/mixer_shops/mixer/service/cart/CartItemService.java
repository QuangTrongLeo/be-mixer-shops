package mixer_shops.mixer.service.cart;

import org.springframework.stereotype.Service;

import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.model.Cart;
import mixer_shops.mixer.model.CartItem;
import mixer_shops.mixer.model.Color;
import mixer_shops.mixer.model.Product;
import mixer_shops.mixer.model.Size;
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
	public void addItemToCart(Long cartId, Long productId, Long colorId, Long sizeId, int quantity) {
	    try {
	    	Cart cart = cartRepository.findById(cartId)
		            .orElseThrow(() -> new ResourcesException("Cart not found!"));
		    Product product = productRepository.findById(productId)
		            .orElseThrow(() -> new ResourcesException("Product not found!"));
		    Color color = product.getColors().stream()
		    		.filter(c -> c.getId().equals(colorId))
		    		.findFirst()
		    		.orElseThrow(() -> new ResourcesException("Color not found!"));
		    
		    Size size = color.getSizes().stream()
		    		.filter(s -> s.getId().equals(sizeId))
		    		.findFirst()
		    		.orElseThrow(() -> new ResourcesException("Size not found!"));

		    CartItem cartItem = cart.getCartItems().stream()
		    		.filter(item -> item.getProduct().getId().equals(productId)
		    				&& item.getColor().getId().equals(colorId)
		    				&& item.getSize().getId().equals(sizeId))
		    		.findFirst().orElse(new CartItem());
		          

		    if (cartItem.getId() == null) {
		        cartItem.setCart(cart);
		        cartItem.setProduct(product);
		        cartItem.setColor(color);
		        cartItem.setSize(size);
		        cartItem.setQuantity(quantity);    
		        cartItem.setPrice(product.getPrice());
		    } else {
		        cartItem.setQuantity(cartItem.getQuantity() + quantity);
		    }
		    
		    cartItem.setTotalPrice();
		    cart.addItem(cartItem);
		    
		    cartItemRepository.save(cartItem);
		    cartRepository.save(cart);
		} catch (OptimisticLockException e) {
			// TODO: handle exception
			throw new ResourcesException("Optimistic Locking failure occurred! Please try again.");
		}
	}

	@Override
	public void updateItemQuantity(Long cartId, Long productId, Long colorId, Long sizeId, int quantity) {
		// TODO Auto-generated method stub
		Cart cart = cartRepository.findById(cartId)
	            .orElseThrow(() -> new ResourcesException("Cart not found!"));
		
		CartItem cartItem = cart.getCartItems().stream()
	            .filter(item -> item.getProduct().getId().equals(productId)
	            		&& item.getColor().getId().equals(colorId)
	            		&& item.getSize().getId().equals(sizeId))
	            .findFirst()
	            .orElseThrow(() -> new ResourcesException("CartItem not found!"));
		
		cartItem.setQuantity(quantity);
		cartItem.setTotalPrice();
		
		int totalAmount = cart.getCartItems()
				.stream().mapToInt(CartItem::getTotalPrice).sum();
		cart.setTotalAmount(totalAmount);
		
		cartItemRepository.save(cartItem);
		cartRepository.save(cart);
	}

	@Override
	public void removeItemToCart(Long cartId, Long productId, Long colorId, Long sizeId) {
		// TODO Auto-generated method stub
		Cart cart = cartRepository.findById(cartId)
	            .orElseThrow(() -> new ResourcesException("Cart not found!"));
		CartItem cartItem = getCartItem(cartId, productId, colorId, sizeId);
		cart.removeItem(cartItem);
		if (cart.getCartItems().isEmpty()) {
			cartRepository.delete(cart);
		} else {
			cartRepository.save(cart);
		}	
	}
	
	@Override
	public CartItem getCartItem(Long cartId, Long productId, Long colorId, Long sizeId) {
		Cart cart = cartRepository.findById(cartId)
	            .orElseThrow(() -> new ResourcesException("Cart not found!"));
		CartItem cartItem = cart.getCartItems().stream()
				.filter(item -> item.getProduct().getId().equals(productId)
						&& item.getColor().getId().equals(colorId)
						&& item.getSize().getId().equals(sizeId))
				.findFirst()
				.orElseThrow(() -> new ResourcesException("Item not found!"));
		return cartItem;
	}

}
