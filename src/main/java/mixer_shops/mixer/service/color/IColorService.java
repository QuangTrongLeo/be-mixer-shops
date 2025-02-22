package mixer_shops.mixer.service.color;

import java.util.List;

import mixer_shops.mixer.dto.ColorDto;
import mixer_shops.mixer.request.AddColorRequest;
import mixer_shops.mixer.request.UpdateColorRequest;

public interface IColorService {
	ColorDto addColorByProductId(AddColorRequest request, Long productId);
	ColorDto getColorById(Long id);
	ColorDto updateColor(UpdateColorRequest request, Long colorId);
	void deleteColorById(Long id);
	List<ColorDto> getAllColors();	
}
