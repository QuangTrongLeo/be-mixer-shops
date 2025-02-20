package mixer_shops.mixer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mixer_shops.mixer.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{
	List<Image> findByProductId(Long id);
}
