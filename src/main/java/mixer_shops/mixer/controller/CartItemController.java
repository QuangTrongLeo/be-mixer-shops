package mixer_shops.mixer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mixer_shops.mixer.response.ApiResponse;
import mixer_shops.mixer.service.cart.ICartItemService;
import mixer_shops.mixer.service.cart.ICartService;

@RestController
@RequestMapping("${api.prefix}/cart-items")
public class CartItemController {
	private final ICartItemService cartItemService;
	private final ICartService cartService;
	
	public CartItemController(ICartItemService cartItemService, ICartService cartService) {
		super();
		this.cartItemService = cartItemService;
		this.cartService = cartService;
	}

	@PostMapping("/item/add")
	public ResponseEntity<ApiResponse> addItemToCart(@RequestParam(required = false) Long cartId, 
													@RequestParam Long productId, 
													@RequestParam int quantity){
		try {
			if (cartId == null) {
				cartId = cartService.initializeNewCart();
			}
			cartItemService.addItemToCart(cartId, productId, quantity);
			return ResponseEntity.ok(new ApiResponse("Success!", null));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@PutMapping("/cart/{cartId}/item/{itemId}/update")
	public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId, 
													@PathVariable Long itemId, 
													@RequestParam int quantity){
		try {
			cartItemService.updateItemQuantity(cartId, itemId, quantity);
			return ResponseEntity.ok(new ApiResponse("Success!", null));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@DeleteMapping("/cart/{cartId}/item/{itemId}/remove")
	public ResponseEntity<ApiResponse> removeItemToCart(@PathVariable Long cartId, 
													@PathVariable Long itemId) {
		try {
			cartItemService.removeItemToCart(cartId, itemId);
			return ResponseEntity.ok(new ApiResponse("Success!", null));
		} catch (Exception e) {
		// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	
}
