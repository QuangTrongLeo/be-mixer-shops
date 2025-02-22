package mixer_shops.mixer.service.color;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import mixer_shops.mixer.dto.ColorDto;
import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.model.Color;
import mixer_shops.mixer.model.Product;
import mixer_shops.mixer.repository.ColorRepository;
import mixer_shops.mixer.repository.ProductRepository;
import mixer_shops.mixer.request.AddColorRequest;
import mixer_shops.mixer.request.UpdateColorRequest;
import mixer_shops.mixer.service.product.IProductService;

@Service
public class ColorService implements IColorService{
	private final ColorRepository colorRepository;
	private final ProductRepository productRepository;
	private final IProductService productService;
	private final ModelMapper modelMapper;

	public ColorService(ColorRepository colorRepository, ProductRepository productRepository, IProductService productService, ModelMapper modelMapper) {
		super();
		this.colorRepository = colorRepository;
		this.productRepository = productRepository;
		this.productService = productService;
		this.modelMapper = modelMapper;
	}

	@Override
	public ColorDto addColorByProductId(AddColorRequest request, Long productId) {
		Product product = productService.getProductById(productId);
		Color color = new Color(request.getName(), request.getHexCode(), product);
		color = colorRepository.save(color);
		return modelMapper.map(color, ColorDto.class);
	}


	@Override
	public ColorDto getColorById(Long id) {
		// TODO Auto-generated method stub
		Color color = colorRepository.findById(id).orElseThrow(() -> new ResourcesException("Color not found!"));
		return modelMapper.map(color, ColorDto.class);
	}
	
	@Override
	public ColorDto updateColor(UpdateColorRequest request, Long colorId) {
	    Color color = colorRepository.findById(colorId)
	            .map(existingColor -> updateExistingColor(existingColor, request))
	            .map(colorRepository::save)
	            .orElseThrow(() -> new ResourcesException("Color not found!"));
	    return modelMapper.map(color, ColorDto.class);
	}

	private Color updateExistingColor(Color color, UpdateColorRequest request) {
	    color.setName(request.getName());
	    color.setHexCode(request.getHexCode());
	    return color;
	}


	@Override
	public void deleteColorById(Long id) {
		// TODO Auto-generated method stub
		// Find color in DB by colorId
		colorRepository.findById(id).ifPresentOrElse(color -> {
			
			// Find product by colorId
			Optional<Product> product = productRepository.findByColorsId(id).stream().findFirst();
			if (product.isPresent()) {
				Product p = product.get();
				p.getColors().remove(color);
				productRepository.save(p);
			}
			
			colorRepository.delete(color);
			colorRepository.flush();
		}, () -> { throw new ResourcesException("Color not found!"); });
	}

	@Override
	public List<ColorDto> getAllColors() {
		// TODO Auto-generated method stub
		List<Color> colors = colorRepository.findAll();
		return colors.stream()
				.map(color -> modelMapper.map(color, ColorDto.class))
				.toList();
	}

}
