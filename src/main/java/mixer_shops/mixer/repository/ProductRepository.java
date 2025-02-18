package mixer_shops.mixer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mixer_shops.mixer.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByCategoryId(Long categoryId);

	List<Product> findByName(String name);

}
