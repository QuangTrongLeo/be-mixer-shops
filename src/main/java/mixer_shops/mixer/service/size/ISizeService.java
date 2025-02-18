package mixer_shops.mixer.service.size;

import java.util.List;

import mixer_shops.mixer.model.Size;
import mixer_shops.mixer.request.AddSizeRequest;
import mixer_shops.mixer.request.UpdateSizeRequest;

public interface ISizeService {
	Size addSize(AddSizeRequest request);
	Size getSizeById(Long id);
	Size updateSize(UpdateSizeRequest request, Long sizeId);
	void deleteSizeById(Long id);
	List<Size> getAllSizes();
}
