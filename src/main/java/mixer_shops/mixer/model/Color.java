package mixer_shops.mixer.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Color {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String hexCode;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@OneToMany(mappedBy = "color", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Size> sizes;

	public Color(Long id, String name, String hexCode, Product product, List<Size> sizes) {
		super();
		this.id = id;
		this.name = name;
		this.hexCode = hexCode;
		this.product = product;
		this.sizes = sizes;
	}

	public Color(String name, String hexCode, Product product) {
		super();
		this.name = name;
		this.hexCode = hexCode;
		this.product = product;
	}

	public Color() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHexCode() {
		return hexCode;
	}

	public void setHexCode(String hexCode) {
		this.hexCode = hexCode;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Size> getSizes() {
		return sizes;
	}

	public void setSizes(List<Size> sizes) {
		this.sizes = sizes;
	}
}
