package mixer_shops.mixer.request;

import lombok.Data;

@Data
public class UpdateCategoryRequest {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
