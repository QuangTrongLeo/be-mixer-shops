package mixer_shops.mixer.service.size;

import java.util.List;

import mixer_shops.mixer.model.Size;

public interface ISizeService {
	Size addSize(Size size);
	Size getSizeById(Long id);
	void deleteSizeById(Long id);
	void updateSize(Size size, Long sizeId);
	List<Size> getAllSizes();
}
