package mixer_shops.mixer.service.color;

import java.util.List;

import mixer_shops.mixer.model.Color;

public interface IColorService {
	Color addColor(Color color);
	Color getColorById(Long id);
	void deleteColorById(Long id);
	void updateColor(Color color, Long colorId);
	List<Color> getAllColors();
}
