package mixer_shops.mixer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mixer_shops.mixer.model.Color;

public interface ColorRepository extends JpaRepository<Color, Long>{

	Color findByName(String name);
	
	List<Color> findByProductId(Long productId);

}
