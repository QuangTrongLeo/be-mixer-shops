package mixer_shops.mixer.service.image;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mixer_shops.mixer.dto.ImageDto;
import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.model.Image;
import mixer_shops.mixer.model.Product;
import mixer_shops.mixer.repository.ImageRepository;
import mixer_shops.mixer.service.product.IProductService;

@Service
public class ImageService implements IImageService{
	private final ImageRepository imageRepository;
	private final IProductService productService;

	public ImageService(ImageRepository imageRepository, IProductService productService) {
		super();
		this.imageRepository = imageRepository;
		this.productService = productService;
	}

	@Override
	public Image getImageById(Long id) {
		// TODO Auto-generated method stub
		return imageRepository.findById(id)
				.orElseThrow(() -> new ResourcesException("Image not found!"));
	}

	@Override
	public List<ImageDto> saveImages(List<MultipartFile> files, Long productId) {
		// TODO Auto-generated method stub
		Product product = productService.getProductById(productId);
		List<ImageDto> saveImageDtos = new ArrayList<ImageDto>();
		for (MultipartFile file : files) {
			try {
				Image image = new Image();
				image.setFileName(file.getOriginalFilename());
				image.setFileType(file.getContentType());
				image.setImage(new SerialBlob(file.getBytes()));
				image.setProduct(product);
				
				String buildDownloadUrl = "/api/mixer-shops/images/image/download/";
				String downloadUrl = buildDownloadUrl + image.getId();
				image.setDownloadUrl(downloadUrl);
				
				Image saveImage = imageRepository.save(image);
				saveImage.setDownloadUrl(buildDownloadUrl + saveImage.getId());
				imageRepository.save(saveImage);
				
				ImageDto imageDto = new ImageDto();
				imageDto.setId(saveImage.getId());
				imageDto.setFileName(saveImage.getFileName());
				imageDto.setDownloadUrl(saveImage.getDownloadUrl());
				saveImageDtos.add(imageDto);
			} catch (IOException | SQLException e) {
				// TODO: handle exception
				throw new RuntimeException(e.getMessage());
			}
		}
		return saveImageDtos;
	}

	@Override
	public void updateImage(MultipartFile file, Long imageId) {
		// TODO Auto-generated method stub
		Image image = getImageById(imageId);
		try {
			image.setFileName(file.getOriginalFilename());
			image.setFileType(file.getContentType());
			image.setImage(new SerialBlob(file.getBytes()));
			imageRepository.save(image);
		} catch (IOException | SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	public void deleteImageById(Long id) {
		// TODO Auto-generated method stub
		imageRepository.findById(id)
			.ifPresentOrElse(imageRepository::delete, () -> { throw new ResourcesException("Image not found");});
	}
}
