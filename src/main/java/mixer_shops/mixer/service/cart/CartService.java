package mixer_shops.mixer.service.cart;

import java.util.HashSet;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import mixer_shops.mixer.dto.CartDto;
import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.model.Cart;
import mixer_shops.mixer.model.CartItem;
import mixer_shops.mixer.repository.CartItemRepository;
import mixer_shops.mixer.repository.CartRepository;

@Service
public class CartService implements ICartService{
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final ModelMapper modelMapper;

	public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository, ModelMapper modelMapper) {
		super();
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public CartDto getCart(Long cartId) {
		// TODO Auto-generated method stub
		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourcesException("Cart not found!"));
		cart.updateTotalAmount();
		cartRepository.save(cart);
		return modelMapper.map(cart, CartDto.class);
	}

	@Override
	public int getTotalPrice(Long cartId) {
		// TODO Auto-generated method stub
		CartDto cartDto = getCart(cartId);
		return cartDto.getCartItems()
				.stream()
				.mapToInt(item -> item.getPrice() * item.getQuantity())
				.sum();
	}
	
	@Override
	public int getTotalQuantity(Long cartId) {
		// TODO Auto-generated method stub
		CartDto cartDto = getCart(cartId);
		return cartDto.getCartItems()
				.stream()
				.mapToInt(item -> item.getQuantity())
				.sum();
	}

	@Override
	public void clearCart(Long cartId) {
		// TODO Auto-generated method stub
		Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourcesException("Cart not found!"));
		cart.getCartItems().clear();
		cartRepository.save(cart);
		cartItemRepository.deleteAllByCartId(cartId);
		cartRepository.deleteById(cartId);
	}
	
	@Override
	public Long initializeNewCart() {
		Cart newCart = new Cart();
		newCart.setTotalAmount(0);
		newCart.setCartItems(new HashSet<CartItem>());
		Cart saveCart = cartRepository.save(newCart);
		return saveCart.getId();
	}

}
