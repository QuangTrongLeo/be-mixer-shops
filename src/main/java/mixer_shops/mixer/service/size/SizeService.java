package mixer_shops.mixer.service.size;

import java.util.List;

import org.springframework.stereotype.Service;

import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.model.Category;
import mixer_shops.mixer.model.Color;
import mixer_shops.mixer.model.Product;
import mixer_shops.mixer.model.Size;
import mixer_shops.mixer.repository.CategoryRepository;
import mixer_shops.mixer.repository.ColorRepository;
import mixer_shops.mixer.repository.ProductRepository;
import mixer_shops.mixer.repository.SizeRepository;
import mixer_shops.mixer.request.AddSizeRequest;
import mixer_shops.mixer.request.UpdateSizeRequest;

@Service
public class SizeService implements ISizeService{
	private final SizeRepository sizeRepository;
	private final ColorRepository colorRepository;
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	public SizeService(SizeRepository sizeRepository, ColorRepository colorRepository,
			ProductRepository productRepository, CategoryRepository categoryRepository) {
		super();
		this.sizeRepository = sizeRepository;
		this.colorRepository = colorRepository;
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Size addSize(AddSizeRequest request) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findByName(request.getColor().getProduct().getCategory().getName());
		if (category == null) {
			category = new Category(request.getColor().getProduct().getCategory().getName());
			category = categoryRepository.save(category);
		}
		
		Product product = productRepository.findFirstByName(request.getColor().getProduct().getName());
		if (product == null) {
			product = new Product(request.getColor().getProduct().getName(), request.getColor().getProduct().getPrice(), category);
			product = productRepository.save(product);
		}
		
		Color color = colorRepository.findByName(request.getColor().getName());
		if (color == null) {
			color = new Color(request.getColor().getName(), request.getColor().getHexCode(), product);
			color = colorRepository.save(color);
		}
		
		request.setColor(color);
		return sizeRepository.save(createSize(request, color));
	}
	
	public Size createSize(AddSizeRequest request, Color color) {
		Size size = new Size(request.getName(), request.getInventory(), color);
		return size;
	}

	@Override
	public Size getSizeById(Long id) {
		// TODO Auto-generated method stub
		return sizeRepository.findById(id).orElseThrow(() -> new ResourcesException("Size not found!"));
	}

	@Override
	public Size updateSize(UpdateSizeRequest request, Long sizeId) {
		// TODO Auto-generated method stub
		Size size = sizeRepository.findById(sizeId)
			.map(existingSize -> updateExistingSize(existingSize, request))
			.map(sizeRepository::save)
			.orElseThrow(() -> new ResourcesException("Size not found!"));
		return size;
	}
	
	private Size updateExistingSize(Size size, UpdateSizeRequest request) {
		size.setName(request.getName());
		size.setInventory(request.getInventory());
		
		Color updateColor = colorRepository.findByName(request.getColor().getName());
		size.setColor(updateColor);
		
		return size;
	}

	@Override
	public void deleteSizeById(Long id) {
		// TODO Auto-generated method stub
		sizeRepository.findById(id).ifPresentOrElse(sizeRepository::delete, () -> { throw new ResourcesException("Size not found!");});
	}

	@Override
	public List<Size> getAllSizes() {
		// TODO Auto-generated method stub
		return sizeRepository.findAll();
	}

}
