package mixer_shops.mixer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mixer_shops.mixer.model.Category;
import mixer_shops.mixer.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByCategoryId(Long categoryId);

	List<Product> findByNameContainingIgnoreCase(String name);
	
	Product findFirstByName(String name);

	Product findByNameAndCategory(String name, Category category);

	List<Product> findByCategory_Name(String name);

	List<Product> findByColorsId(Long colorId);
	
	List<Product> findByColors_NameIgnoreCase(String colorName);

	List<Product> findByCategory_NameIgnoreCase(String categoryName);

	List<Product> findByCategory_NameAndColors_NameIgnoreCase(String categoryName, String colorName);
	
	List<Product> findByNameContainingIgnoreCaseAndColors_NameIgnoreCase(String productName, String colorName);

}
