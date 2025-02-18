package mixer_shops.mixer.service.product;

import java.util.List;

import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.model.Product;
import mixer_shops.mixer.repository.ProductRepository;

public class ProductService implements IProductService{
	private final ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
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
			.ifPresentOrElse(productRepository::delete, () -> new ResourcesException("Product not found!"));
	}

	@Override
	public void updateProduct(Product product, Long productId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public List<Product> getProductsByCategoryId(Long categoryId) {
		// TODO Auto-generated method stub
		return productRepository.findByCategoryId(categoryId);
	}

	@Override
	public List<Product> getProductsByName(String name) {
		// TODO Auto-generated method stub
		return productRepository.findByName(name);
	}
	
}
