package mixer_shops.mixer.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import mixer_shops.mixer.model.User;

@Data
public class CartDto {
	private Long id;
	private int totalAmount;
	private Set<CartItemDto> cartItems = new HashSet<CartItemDto>();
	private User user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Set<CartItemDto> getCartItems() {
		return cartItems;
	}
	public void setCartItems(Set<CartItemDto> cartItems) {
		this.cartItems = cartItems;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
