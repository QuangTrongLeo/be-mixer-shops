package mixer_shops.mixer.service.product;

import java.util.List;

import mixer_shops.mixer.model.Product;
import mixer_shops.mixer.request.AddProductRequest;
import mixer_shops.mixer.request.UpdateProductRequest;

public interface IProductService {
	Product addProduct(AddProductRequest request);
	Product getProductById(Long id);
	Product updateProduct(UpdateProductRequest request, Long productId);
	void deleteProductById(Long id);
	List<Product> getAllProducts();
	List<Product> getProductsByCategoryId(Long categoryId);
	List<Product> getProductsByName(String name);
}
