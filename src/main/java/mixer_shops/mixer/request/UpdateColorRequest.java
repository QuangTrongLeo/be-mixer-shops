package mixer_shops.mixer.request;

import java.util.List;

import lombok.Data;
import mixer_shops.mixer.model.Product;
import mixer_shops.mixer.model.Size;

@Data
public class UpdateColorRequest {
	private Long id;
	private String name;
	private String hexCode;
	private Product product;
	private List<Size> sizes;
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
