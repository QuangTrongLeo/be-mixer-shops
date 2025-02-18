package mixer_shops.mixer.service.size;

import java.util.List;

import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.model.Size;
import mixer_shops.mixer.repository.SizeRepository;

public class SizeService{
	private final SizeRepository sizeRepository;
	
	public SizeService(SizeRepository sizeRepository) {
		super();
		this.sizeRepository = sizeRepository;
	}

}
