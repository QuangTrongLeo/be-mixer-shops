package mixer_shops.mixer.request;

import lombok.Data;

@Data
public class UpdateColorRequest {
	private String name;
	private String hexCode;
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
}
