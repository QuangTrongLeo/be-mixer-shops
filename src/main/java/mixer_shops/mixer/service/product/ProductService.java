package mixer_shops.mixer.service.product;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import mixer_shops.mixer.dto.ImageDto;
import mixer_shops.mixer.dto.ProductDto;
import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.model.Category;
import mixer_shops.mixer.model.Image;
import mixer_shops.mixer.model.Product;
import mixer_shops.mixer.repository.CategoryRepository;
import mixer_shops.mixer.repository.ImageRepository;
import mixer_shops.mixer.repository.ProductRepository;
import mixer_shops.mixer.request.AddProductRequest;
import mixer_shops.mixer.request.UpdateProductRequest;

@Service
public class ProductService implements IProductService{
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final ImageRepository imageRepository;
	private final ModelMapper modelMapper;
	
	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ImageRepository imageRepository, ModelMapper modelMapper) {
		super();
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.imageRepository = imageRepository;
		this.modelMapper = new ModelMapper();
	}

	@Override
	public Product addProduct(AddProductRequest request) {
		// TODO Auto-generated method stub
		// check if the category is found in the DB
		// if yes, set it as the new product category
		// The set as the new product category
		Category category = categoryRepository.findByName(request.getCategory().getName());
	    if (category == null) {
	        category = new Category(request.getCategory().getName());
	        category = categoryRepository.save(category);
	    }
	    request.setCategory(category);
	    return productRepository.save(createProduct(request, category));
	}
	
	private Product createProduct(AddProductRequest request, Category category) {
		Product product = new Product(
					request.getName(),
					request.getPrice(),
					category
				);
		return product;
	}

	@Override
	public Product getProductById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id)
				.orElseThrow(() -> new ResourcesException("Product not found!"));
	}

	@Override
	public void deleteProductById(Long id) {
		// TODO Auto-generated method stub
		productRepository.findById(id)
			.ifPresentOrElse(product -> {
				product.setCategory(null);
				productRepository.save(product);
				productRepository.delete(product);
			}, () -> { 
				throw new ResourcesException("Product not found!"); 
			});
	}

	@Override
	public Product updateProduct(UpdateProductRequest request, Long productId) {
		// TODO Auto-generated method stub
		Product product = productRepository.findById(productId)
				.map(existingProduct -> updateExistingProduct(existingProduct, request))
				.map(productRepository::save)
				.orElseThrow(() -> new ResourcesException("Product not found!"));
		return product;
	}
	
	private Product updateExistingProduct(Product product, UpdateProductRequest request) {
		product.setName(request.getName());
		product.setPrice(request.getPrice());
		
		Category updateCategory = categoryRepository.findByName(request.getCategory().getName());
		product.setCategory(updateCategory);
		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public List<Product> getProductsByCategory(String name) {
		// TODO Auto-generated method stub
		return productRepository.findByCategory_Name(name);
	}

	@Override
	public List<Product> getProductsByName(String name) {
		// TODO Auto-generated method stub
		return productRepository.findByNameIgnoreCase(name);
	}
	
	@Override
	public List<ProductDto> getConvertedProducts(List<Product> products){
		return products.stream().map(this::convertoDto).toList();
	}
	
	@Override
	public ProductDto convertoDto(Product product) {
		ProductDto productDto = modelMapper.map(product, ProductDto.class);
		List<Image> images = imageRepository.findByProductId(product.getId());
		List<ImageDto> imageDtos = images.stream()
				.map(image -> modelMapper.map(image, ImageDto.class)).toList();
		productDto.setImages(imageDtos);
		return productDto;
	}
}
