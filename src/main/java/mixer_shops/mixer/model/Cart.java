package mixer_shops.mixer.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Version;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
    private Integer version;
	private int totalAmount;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CartItem> cartItems = new HashSet<CartItem>();
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Cart(Long id, int totalAmount, Set<CartItem> cartItems, User user) {
		super();
		this.id = id;
		this.totalAmount = totalAmount;
		this.cartItems = cartItems;
		this.user = user;
	}

	public Cart() {
		super();
	}

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

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addItem(CartItem item) {
		this.cartItems.add(item);
		item.setCart(this);
		updateTotalAmount();
	}
	
	public void removeItem(CartItem item) {
		this.cartItems.remove(item);
		item.setCart(null);
		updateTotalAmount();
	}
	
	public void updateTotalAmount() {
		this.totalAmount = cartItems.stream()
				.mapToInt(item -> item.getPrice() * item.getQuantity())
				.sum();
	}
}
