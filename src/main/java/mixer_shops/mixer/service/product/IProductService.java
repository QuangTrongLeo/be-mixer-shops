package mixer_shops.mixer.service.product;

import java.util.List;

import mixer_shops.mixer.dto.ProductDto;
import mixer_shops.mixer.model.Product;
import mixer_shops.mixer.request.AddProductRequest;
import mixer_shops.mixer.request.UpdateProductRequest;

public interface IProductService {
	ProductDto addProductByCategoryId(AddProductRequest request, Long categoryId);
	ProductDto getProductById(Long id);
	ProductDto updateProduct(UpdateProductRequest request, Long productId);
	void deleteProductById(Long id);
	List<Product> getAllProducts();
	List<ProductDto> getProductsByCategory(Long categoryId);
	List<ProductDto> getProductsByCategoryName(String categoryName);
	List<ProductDto> getProductsByName(String name);
	
	ProductDto convertoDto(Product product);
	List<ProductDto> getConvertedProducts(List<Product> products);
}
