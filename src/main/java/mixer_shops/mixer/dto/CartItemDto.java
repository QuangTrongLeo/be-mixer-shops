package mixer_shops.mixer.dto;

import lombok.Data;

@Data
public class CartItemDto {
	private Long id;
	private int quantity;
	private int price;
	private int totalPrice;
	private ProductSummaryDto product;
	private ColorSummaryDto color;
	private SizeDto size;
	
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
	public ProductSummaryDto getProduct() {
		return product;
	}
	public void setProduct(ProductSummaryDto product) {
		this.product = product;
	}
	public ColorSummaryDto getColor() {
		return color;
	}
	public void setColor(ColorSummaryDto color) {
		this.color = color;
	}
	public SizeDto getSize() {
		return size;
	}
	public void setSize(SizeDto size) {
		this.size = size;
	}
	
	
}
