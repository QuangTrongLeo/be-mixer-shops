package mixer_shops.mixer.service.product;

import java.util.List;

import mixer_shops.mixer.model.Product;

public interface IProductService {
	Product addProduct(Product product);
	Product getProductById(Long id);
	void deleteProductById(Long id);
	void updateProduct(Product product, Long productId);
	List<Product> getAllProducts();
	List<Product> getProductsByCategoryId(Long categoryId);
	List<Product> getProductsByName(String name);
}
