package mixer_shops.mixer.dto;

import java.util.List;

import lombok.Data;
import mixer_shops.mixer.model.Category;

@Data
public class ProductDto {
	private Long id;
	private String name;
	private int price;
	private Category category;
	private List<ImageDto> images;
	private List<ColorDto> colors;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public List<ImageDto> getImages() {
		return images;
	}
	public void setImages(List<ImageDto> images) {
		this.images = images;
	}
	public List<ColorDto> getColors() {
		return colors;
	}
	public void setColors(List<ColorDto> colors) {
		this.colors = colors;
	}
	
}
