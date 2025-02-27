package mixer_shops.mixer.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mixer_shops.mixer.dto.OrderDto;
import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.response.ApiResponse;
import mixer_shops.mixer.service.order.IOrderService;

@RestController
@RequestMapping("${api.prefix}/orders")
public class OrderContrller {
	private final IOrderService orderService;

	public OrderContrller(IOrderService orderService) {
		super();
		this.orderService = orderService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> createOrder(@RequestParam Long userId){
		try {
			OrderDto orderDto = orderService.placeOrder(userId);
			return ResponseEntity.ok(new ApiResponse("Success!", orderDto));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@GetMapping("/{orderId}/order")
	public ResponseEntity<ApiResponse> getOrderById(@PathVariable Long orderId){
		try {
			OrderDto orderDto = orderService.getOrder(orderId);
			return ResponseEntity.ok(new ApiResponse("Success!", orderDto));
		} catch (ResourcesException e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}
	}

	@GetMapping("/user/{userId}/orders")
	public ResponseEntity<ApiResponse> getUserOrders(@PathVariable Long userId){
		try {
			List<OrderDto> orderDtos = orderService.getUserOrders(userId);
			return ResponseEntity.ok(new ApiResponse("Success!", orderDtos));
		} catch (ResourcesException e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}
	}
}
