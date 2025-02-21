package mixer_shops.mixer.dto;

import java.util.List;

import lombok.Data;

@Data
public class ColorDto {
	private Long id;
	private String name;
	private String hexCode;
	private List<SizeDto> sizes;
	
	public List<SizeDto> getSizes() {
		return sizes;
	}
	public void setSizes(List<SizeDto> sizes) {
		this.sizes = sizes;
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
	
}
