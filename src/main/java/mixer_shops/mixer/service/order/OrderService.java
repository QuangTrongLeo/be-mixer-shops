package mixer_shops.mixer.service.order;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import mixer_shops.mixer.dto.OrderDto;
import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.model.Cart;
import mixer_shops.mixer.model.Order;
import mixer_shops.mixer.model.OrderItem;
import mixer_shops.mixer.model.Product;
import mixer_shops.mixer.model.Size;
import mixer_shops.mixer.repository.OrderRepository;

@Service
public class OrderService implements IOrderService{
	private final OrderRepository orderRepository;
	private final ModelMapper modelMapper;
	
	public OrderService(OrderRepository orderRepository, ModelMapper modelMapper) {
		super();
		this.orderRepository = orderRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public OrderDto placeOrder(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<OrderItem> createOrderItems(Order order, Cart cart) {
	    List<OrderItem> orderItems = cart.getCartItems().stream().map(cartItem -> {
	        Product product = cartItem.getProduct();
	        
	        // Duyệt qua các màu sắc (Color) của sản phẩm
	        product.getColors().forEach(color -> {
	            // Duyệt qua các kích cỡ (Size) của mỗi màu sắc
	            color.getSizes().forEach(size -> {
	                // Cập nhật số lượng tồn kho (inventory) của mỗi kích cỡ
	                // Giảm số lượng tồn kho theo số lượng trong giỏ hàng
	                size.setInventory(size.getInventory() - cartItem.getQuantity());
	            });
	        });

	        // Tạo OrderItem từ CartItem
	        OrderItem orderItem = new OrderItem();
	        orderItem.setProduct(product);
	        orderItem.setQuantity(cartItem.getQuantity());
	        orderItem.setPrice(product.getPrice()); // Lấy giá sản phẩm
	        orderItem.setOrder(order); // Gắn đơn hàng vào OrderItem
	        
	        return orderItem;
	    }).collect(Collectors.toList()); // Chuyển đổi thành danh sách OrderItem

	    return orderItems;
	}

	
	private int calculateTotalAmount(List<OrderItem> items) {
		return items.stream().mapToInt(item -> 
			item.getPrice() * item.getQuantity()
		).sum();
	}
	
	@Override
	public int getTotalQuantity(List<OrderItem> items) {
		return items.stream().mapToInt(item -> item.getQuantity()).sum();
	}

	@Override
	public OrderDto getOrder(Long orderId) {
		// TODO Auto-generated method stub
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new ResourcesException("Order not found!"));
		return modelMapper.map(order, OrderDto.class);
	}
	
}
