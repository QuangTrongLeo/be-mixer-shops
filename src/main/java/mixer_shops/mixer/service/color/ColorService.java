package mixer_shops.mixer.service.color;

import java.util.List;

import org.springframework.stereotype.Service;

import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.model.Color;
import mixer_shops.mixer.model.Product;
import mixer_shops.mixer.repository.ColorRepository;
import mixer_shops.mixer.repository.ProductRepository;
import mixer_shops.mixer.request.AddColorRequest;
import mixer_shops.mixer.request.UpdateColorRequest;

@Service
public class ColorService implements IColorService{
	private final ColorRepository colorRepository;
	private final ProductRepository productRepository;

	public ColorService(ColorRepository colorRepository, ProductRepository productRepository) {
		super();
		this.colorRepository = colorRepository;
		this.productRepository = productRepository;
	}

	@Override
	public Color addColor(AddColorRequest request, Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourcesException("Product not found!"));
		
		// Create color from request
		Color color = createColor(request, product);
		
		color = colorRepository.save(color);

	    return color;
	}

	
	private Color createColor(AddColorRequest request, Product product) {
		Color color = new Color(request.getName(), request.getHexCode(), product);
		return color;
	}

	@Override
	public Color getColorById(Long id) {
		// TODO Auto-generated method stub
		return colorRepository.findById(id).orElseThrow(() -> new ResourcesException("Color not found!"));
	}
	
	@Override
	public Color updateColor(UpdateColorRequest request, Long colorId) {
	    Color color = colorRepository.findById(colorId)
	            .map(existingColor -> updateExistingColor(existingColor, request))
	            .map(colorRepository::save)
	            .orElseThrow(() -> new ResourcesException("Color not found!"));
	    return color;
	}

	private Color updateExistingColor(Color color, UpdateColorRequest request) {
	    color.setName(request.getName());
	    color.setHexCode(request.getHexCode());
	    
	    Product updateProduct = productRepository.findFirstByName(request.getProduct().getName());
	    color.setProduct(updateProduct);
	    return color;
	}


	@Override
	public void deleteColorById(Long id) {
		// TODO Auto-generated method stub
		colorRepository.findById(id).ifPresentOrElse(colorRepository::delete, () -> { throw new ResourcesException("Color not found!"); });
	}

	@Override
	public List<Color> getAllColors() {
		// TODO Auto-generated method stub
		return colorRepository.findAll();
	}

}
