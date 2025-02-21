package mixer_shops.mixer.service.size;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import mixer_shops.mixer.dto.SizeDto;
import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.model.Color;
import mixer_shops.mixer.model.Size;
import mixer_shops.mixer.repository.ColorRepository;
import mixer_shops.mixer.repository.SizeRepository;
import mixer_shops.mixer.request.AddSizeRequest;
import mixer_shops.mixer.request.UpdateSizeRequest;

@Service
public class SizeService implements ISizeService{
	private final SizeRepository sizeRepository;
	private final ColorRepository colorRepository;
	private final ModelMapper modelMapper;

	public SizeService(SizeRepository sizeRepository, ColorRepository colorRepository, ModelMapper modelMapper) {
		super();
		this.sizeRepository = sizeRepository;
		this.colorRepository = colorRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public SizeDto addSizeByColorId(AddSizeRequest request, Long colorId) {
		// TODO Auto-generated method stub
		Color color = colorRepository.findById(colorId).orElseThrow(() -> new ResourcesException("Color not found!"));
		Size size = new Size(request.getName(), request.getInventory(), color);
		size = sizeRepository.save(size);
		return modelMapper.map(size, SizeDto.class);
	}

	@Override
	public Size getSizeById(Long id) {
		// TODO Auto-generated method stub
		return sizeRepository.findById(id).orElseThrow(() -> new ResourcesException("Size not found!"));
	}

	@Override
	public Size updateSize(UpdateSizeRequest request, Long sizeId) {
		// TODO Auto-generated method stub
		Size size = sizeRepository.findById(sizeId)
			.map(existingSize -> updateExistingSize(existingSize, request))
			.map(sizeRepository::save)
			.orElseThrow(() -> new ResourcesException("Size not found!"));
		return size;
	}
	
	private Size updateExistingSize(Size size, UpdateSizeRequest request) {
		size.setName(request.getName());
		size.setInventory(request.getInventory());
		
		Color updateColor = colorRepository.findByName(request.getColor().getName());
		size.setColor(updateColor);
		
		return size;
	}

	@Override
	public void deleteSizeById(Long id) {
		// TODO Auto-generated method stub
		sizeRepository.findById(id).ifPresentOrElse(sizeRepository::delete, () -> { throw new ResourcesException("Size not found!");});
	}

	@Override
	public List<Size> getAllSizes() {
		// TODO Auto-generated method stub
		return sizeRepository.findAll();
	}

}
