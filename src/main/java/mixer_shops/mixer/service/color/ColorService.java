package mixer_shops.mixer.service.color;

import java.util.List;

import org.springframework.stereotype.Service;

import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.model.Color;
import mixer_shops.mixer.repository.ColorRepository;

@Service
public class ColorService implements IColorService{
	private final ColorRepository colorRepository;

	public ColorService(ColorRepository colorRepository) {
		super();
		this.colorRepository = colorRepository;
	}

	@Override
	public Color addColor(Color color) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getColorById(Long id) {
		// TODO Auto-generated method stub
		return colorRepository.findById(id).orElseThrow(() -> new ResourcesException("Color not found!"));
	}

	@Override
	public void deleteColorById(Long id) {
		// TODO Auto-generated method stub
		colorRepository.findById(id).ifPresentOrElse(colorRepository::delete, () -> new ResourcesException("Color not found!"));
	}

	@Override
	public void updateColor(Color color, Long colorId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Color> getAllColors() {
		// TODO Auto-generated method stub
		return colorRepository.findAll();
	}

}
