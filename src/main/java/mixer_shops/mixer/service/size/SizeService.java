package mixer_shops.mixer.service.size;

import java.util.List;

import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.model.Size;
import mixer_shops.mixer.repository.SizeRepository;

public class SizeService implements ISizeService{
	private final SizeRepository sizeRepository;
	
	public SizeService(SizeRepository sizeRepository) {
		super();
		this.sizeRepository = sizeRepository;
	}

	@Override
	public Size addSize(Size size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Size getSizeById(Long id) {
		// TODO Auto-generated method stub
		return sizeRepository.findById(id).orElseThrow(() -> new ResourcesException("Size not found!"));
	}

	@Override
	public void deleteSizeById(Long id) {
		// TODO Auto-generated method stub
		sizeRepository.findById(id).ifPresentOrElse(sizeRepository::delete, () -> new ResourcesException("Size not found!"));
		
	}

	@Override
	public void updateSize(Size size, Long sizeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Size> getAllSizes() {
		// TODO Auto-generated method stub
		return sizeRepository.findAll();
	}

}
