package mixer_shops.mixer.service.image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import mixer_shops.mixer.dto.ImageDto;
import mixer_shops.mixer.model.Image;

public interface IImageService {
	Image getImageById(Long id);
	List<ImageDto> saveImages(List<MultipartFile> file, Long productId);
	void updateImage(MultipartFile file, Long imageId);
	void deleteImageById(Long id);
	List<Image> getAllImages();
}
