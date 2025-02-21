package mixer_shops.mixer.dto;

import lombok.Data;

@Data
public class SizeDto {
	private Long id;
	private String name;
	private int inventory;
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
	
}
