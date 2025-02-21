package mixer_shops.mixer.request;

import lombok.Data;

@Data
public class AddSizeRequest {
	private String name;
	private int inventory;
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
