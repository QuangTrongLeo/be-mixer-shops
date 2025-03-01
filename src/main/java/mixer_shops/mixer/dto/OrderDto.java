package mixer_shops.mixer.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.Data;
import mixer_shops.mixer.enums.OrderStatus;

@Data
public class OrderDto {
	private Long id;
	private LocalDate orderDate;
	private int totalAmount;
	private OrderStatus orderStatus;
	private Set<OrderItemDto> orderItems;
//	private User user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Set<OrderItemDto> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItemDto> orderItems) {
		this.orderItems = orderItems;
	}
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
	
}
