package mixer_shops.mixer.service.order;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import mixer_shops.mixer.dto.OrderDto;
import mixer_shops.mixer.enums.OrderStatus;
import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.model.Cart;
import mixer_shops.mixer.model.Color;
import mixer_shops.mixer.model.Order;
import mixer_shops.mixer.model.OrderItem;
import mixer_shops.mixer.model.Product;
import mixer_shops.mixer.model.Size;
import mixer_shops.mixer.repository.CartRepository;
import mixer_shops.mixer.repository.OrderRepository;
import mixer_shops.mixer.service.cart.CartService;

@Service
public class OrderService implements IOrderService{
	private final OrderRepository orderRepository;
	private final CartRepository cartRepository;
	private final CartService cartService;
	private final ModelMapper modelMapper;


	public OrderService(OrderRepository orderRepository, CartRepository cartRepository, CartService cartService,
			ModelMapper modelMapper) {
		super();
		this.orderRepository = orderRepository;
		this.cartRepository = cartRepository;
		this.cartService = cartService;
		this.modelMapper = modelMapper;
	}

	@Override
	public OrderDto placeOrder(Long userId) {
		// TODO Auto-generated method stub
		Cart cart = cartRepository.findByUserId(userId);
		if (cart == null) {
			throw new ResourcesException("Cart not found for user with ID: " + userId);
		}
		
		Order order = createOrder(cart);
		List<OrderItem> orderItems = createOrderItems(order, cart);
		order.setOrderItems(new HashSet<OrderItem>(orderItems));
		order.setTotalAmount(calculateTotalAmount(orderItems));
		
		Order saveOrder = orderRepository.save(order);
		
		cartService.clearCart(cart.getId());
		
		return modelMapper.map(saveOrder, OrderDto.class);
	}
	
	private Order createOrder(Cart cart) {
		Order order = new Order();
		order.setUser(cart.getUser());
		order.setOrderStatus(OrderStatus.PENDING);
		order.setOrderDate(LocalDate.now());
		return order;
	}
	
	private List<OrderItem> createOrderItems(Order order, Cart cart) {
	    List<OrderItem> orderItems = cart.getCartItems().stream().map(cartItem -> {
	        Product product = cartItem.getProduct();
	        
	        product.getColors().forEach(color -> {
	            color.getSizes().forEach(size -> {
	                size.setInventory(size.getInventory() - cartItem.getQuantity());
	            });
	        });

	        // Tạo OrderItem từ CartItem
	        OrderItem orderItem = new OrderItem();
	        orderItem.setProduct(product);
	        orderItem.setQuantity(cartItem.getQuantity());
	        orderItem.setPrice(product.getPrice());
	        orderItem.setOrder(order);
	        
	        Color selectedColor = cartItem.getColor();
	        Size selectedSize = cartItem.getSize();
	        
	        orderItem.setColor(selectedColor);
	        orderItem.setSize(selectedSize);
	        
	        return orderItem;
	    }).collect(Collectors.toList());

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
	
	@Override
	public List<OrderDto> getUserOrders(Long userId){
		List<Order> orders = orderRepository.findByUserId(userId);
		return orders.stream()
				.map(order -> modelMapper
						.map(order, OrderDto.class))
				.toList();
	}
}
