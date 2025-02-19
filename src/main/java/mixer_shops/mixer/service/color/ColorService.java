package mixer_shops.mixer.service.color;

import java.util.List;

import org.springframework.stereotype.Service;

import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.model.Category;
import mixer_shops.mixer.model.Color;
import mixer_shops.mixer.model.Product;
import mixer_shops.mixer.repository.CategoryRepository;
import mixer_shops.mixer.repository.ColorRepository;
import mixer_shops.mixer.repository.ProductRepository;
import mixer_shops.mixer.request.AddColorRequest;
import mixer_shops.mixer.request.UpdateColorRequest;

@Service
public class ColorService implements IColorService{
	private final ColorRepository colorRepository;
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	public ColorService(ColorRepository colorRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
		super();
		this.colorRepository = colorRepository;
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Color addColor(AddColorRequest request) {
	    Category category = categoryRepository.findByName(request.getProduct().getCategory().getName());
	    if (category == null) {
	        category = new Category(request.getProduct().getCategory().getName());
	        category = categoryRepository.save(category);
	    }

	    Product product = productRepository.findByNameAndCategory(request.getProduct().getName(), category);
	    if (product == null) {
	        product = new Product(request.getProduct().getName(), request.getProduct().getPrice(), category);
	        product = productRepository.save(product);
	    }

	    request.setProduct(product);
	    return colorRepository.save(createColor(request, product));
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
