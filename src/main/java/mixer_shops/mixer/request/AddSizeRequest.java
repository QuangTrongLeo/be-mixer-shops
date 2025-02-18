package mixer_shops.mixer.request;

import lombok.Data;
import mixer_shops.mixer.model.Color;

@Data
public class AddSizeRequest {
	private Long id;
	private String name;
	private int inventory;
	private Color color;
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
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
}
