package mixer_shops.mixer.service.cart;

import java.util.HashSet;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import mixer_shops.mixer.dto.CartDto;
import mixer_shops.mixer.dto.UserDto;
import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.model.Cart;
import mixer_shops.mixer.model.CartItem;
import mixer_shops.mixer.model.User;
import mixer_shops.mixer.repository.CartItemRepository;
import mixer_shops.mixer.repository.CartRepository;
import mixer_shops.mixer.repository.UserRepository;

@Service
public class CartService implements ICartService{
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository,
			UserRepository userRepository, ModelMapper modelMapper) {
		super();
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.userRepository = userRepository;
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
		Cart cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new ResourcesException("Cart not found!"));
		
		// Xóa tất cả các item trong giỏ hàng
		cart.getCartItems().clear();
		
		cartRepository.save(cart);
		
		if (cart.getCartItems().isEmpty()) {
	        cartItemRepository.deleteAllByCartId(cartId); // Xóa các cartItem
	        cartRepository.deleteById(cartId); // Xóa giỏ hàng
	    }
	}
	
	@Override
	public CartDto initializeNewCart(UserDto userDto) {
		User user = userRepository.findById(userDto.getId())
				.orElseThrow(() -> new ResourcesException("User not found!"));
		Cart newCart = new Cart();
		newCart.setTotalAmount(0);
		newCart.setCartItems(new HashSet<CartItem>());
		
		newCart.setUser(user);
		
		Cart saveCart = cartRepository.save(newCart);
		
		saveCart.updateTotalAmount();
		
		return modelMapper.map(saveCart, CartDto.class);
	}

}
