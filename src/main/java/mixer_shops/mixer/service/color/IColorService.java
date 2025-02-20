package mixer_shops.mixer.service.color;

import java.util.List;

import mixer_shops.mixer.model.Color;
import mixer_shops.mixer.request.AddColorRequest;
import mixer_shops.mixer.request.UpdateColorRequest;

public interface IColorService {
	Color addColor(AddColorRequest request, Long productId);
	Color getColorById(Long id);
	Color updateColor(UpdateColorRequest request, Long colorId);
	void deleteColorById(Long id);
	List<Color> getAllColors();	
}
