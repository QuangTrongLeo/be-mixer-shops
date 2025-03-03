package mixer_shops.mixer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mixer_shops.mixer.dto.CartDto;
import mixer_shops.mixer.dto.UserDto;
import mixer_shops.mixer.response.ApiResponse;
import mixer_shops.mixer.service.cart.ICartItemService;
import mixer_shops.mixer.service.cart.ICartService;
import mixer_shops.mixer.service.user.IUserService;

@CrossOrigin(origins = "${api.host}")
@RestController
@RequestMapping("${api.prefix}/cart-items")
public class CartItemController {
	private final ICartItemService cartItemService;
	private final ICartService cartService;
	private final IUserService userService;

	public CartItemController(ICartItemService cartItemService, ICartService cartService, IUserService userService) {
		super();
		this.cartItemService = cartItemService;
		this.cartService = cartService;
		this.userService = userService;
	}

	@PostMapping("/item/add")
	public ResponseEntity<ApiResponse> addItemToCart(
													@RequestParam Long productId, 
													@RequestParam Long colorId,
													@RequestParam Long sizeId,
													@RequestParam int quantity){
		try {
			UserDto userDto = userService.getUserById(1L);
			CartDto cartDto = cartService.initializeNewCart(userDto);
			cartItemService.addItemToCart(cartDto.getId(), productId, colorId, sizeId, quantity);
			return ResponseEntity.ok(new ApiResponse("Success!", null));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@PutMapping("/cart/{cartId}/item/{itemId}/color/{colorId}/size/{sizeId}/update")
	public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId, 
													@PathVariable Long itemId,
													@PathVariable Long colorId,
													@PathVariable Long sizeId,
													@RequestParam int quantity){
		try {
			cartItemService.updateItemQuantity(cartId, itemId, colorId, sizeId, quantity);
			return ResponseEntity.ok(new ApiResponse("Success!", null));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@DeleteMapping("/cart/{cartId}/item/{itemId}/color/{colorId}/size/{sizeId}/remove")
	public ResponseEntity<ApiResponse> removeItemToCart(@PathVariable Long cartId, 
													@PathVariable Long itemId,
													@PathVariable Long colorId,
													@PathVariable Long sizeId) {
		try {
			cartItemService.removeItemToCart(cartId, itemId, colorId, sizeId);
			return ResponseEntity.ok(new ApiResponse("Success!", null));
		} catch (Exception e) {
		// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	
}
