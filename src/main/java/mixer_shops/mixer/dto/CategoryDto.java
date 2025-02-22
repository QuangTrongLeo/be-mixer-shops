package mixer_shops.mixer.dto;

import java.util.List;

import lombok.Data;

@Data
public class CategoryDto {
	private Long id;
	private String name;
	private List<ProductDto> products;
	
	public List<ProductDto> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDto> products) {
		this.products = products;
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
}
