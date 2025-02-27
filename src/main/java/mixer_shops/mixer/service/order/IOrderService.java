package mixer_shops.mixer.service.order;

import java.util.List;

import mixer_shops.mixer.dto.OrderDto;
import mixer_shops.mixer.model.OrderItem;

public interface IOrderService {
	OrderDto placeOrder(Long userId);
	OrderDto getOrder(Long orderId);
	int getTotalQuantity(List<OrderItem> items);
	List<OrderDto> getUserOrders(Long userId);
}
