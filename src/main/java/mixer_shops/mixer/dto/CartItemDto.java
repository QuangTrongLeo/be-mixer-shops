package mixer_shops.mixer.dto;

import lombok.Data;

@Data
public class CartItemDto {
	private Long id;
	private int quantity;
	private int price;
	private int totalPrice;
	private ProductDto product;
//	private CartDto cart;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public ProductDto getProduct() {
		return product;
	}
	public void setProduct(ProductDto product) {
		this.product = product;
	}
//	public CartDto getCart() {
//		return cart;
//	}
//	public void setCart(CartDto cart) {
//		this.cart = cart;
//	}
	
	
}
