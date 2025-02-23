package mixer_shops.mixer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mixer_shops.mixer.dto.CartDto;
import mixer_shops.mixer.response.ApiResponse;
import mixer_shops.mixer.service.cart.ICartService;

@RestController
@RequestMapping("${api.prefix}/carts")
public class CartController {
	private final ICartService cartService;
	
	public CartController(ICartService cartService) {
		super();
		this.cartService = cartService;
	}

	@GetMapping("/{cartId}/my-cart")
	public ResponseEntity<ApiResponse> getCart(@PathVariable Long cartId) {
		try {
			CartDto cartDto = cartService.getCart(cartId);
			return ResponseEntity.ok(new ApiResponse("Success!", cartDto));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Cart not found", null));
		}
	}
	
	@GetMapping("/{cartId}/total-price")
	public ResponseEntity<ApiResponse> getTotalPrice(@PathVariable Long cartId) {
		try {
			int totalPrice = cartService.getTotalPrice(cartId);
			return ResponseEntity.ok(new ApiResponse("Success!", totalPrice));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@DeleteMapping("/{cartId}/clear")
	public ResponseEntity<ApiResponse> clearCart(@PathVariable Long cartId) {
		try {
			cartService.clearCart(cartId);
			return ResponseEntity.ok(new ApiResponse("Success!", null));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Cart not found", null));
		}
	}
}
