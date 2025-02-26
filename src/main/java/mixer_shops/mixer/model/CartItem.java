package mixer_shops.mixer.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int quantity;
	private int price;
	private int totalPrice;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id")
	private Cart cart;

	public CartItem(Long id, int quantity, int price, int totalPrice, Product product, Cart cart) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.totalPrice = totalPrice;
		this.product = product;
		this.cart = cart;
	}

	public CartItem() {
		super();
	}

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

	public void setTotalPrice() {
		this.totalPrice = this.price * this.quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
}
