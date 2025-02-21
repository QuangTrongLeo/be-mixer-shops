package mixer_shops.mixer.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mixer_shops.mixer.dto.SizeDto;
import mixer_shops.mixer.model.Size;
import mixer_shops.mixer.request.AddSizeRequest;
import mixer_shops.mixer.request.UpdateSizeRequest;
import mixer_shops.mixer.response.ApiResponse;
import mixer_shops.mixer.service.size.ISizeService;

@RestController
@RequestMapping("${api.prefix}/sizes")
public class SizeController {
	private final ISizeService sizeService;

	public SizeController(ISizeService sizeService) {
		super();
		this.sizeService = sizeService;
	}
	
	@GetMapping("/size/{sizeId}")
	public ResponseEntity<ApiResponse> getSizeById(@PathVariable Long sizeId){
		try {
			Size size = sizeService.getSizeById(sizeId);
			return ResponseEntity.ok(new ApiResponse("Success!", size));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error!", null));
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addSizeByColorId(@RequestBody AddSizeRequest request, @RequestParam Long colorId){
		try {
			SizeDto sizeDto = sizeService.addSizeByColorId(request, colorId);
			return ResponseEntity.ok(new ApiResponse("Success!", sizeDto));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Error!", null));
		}
	}
	
	@PutMapping("/size/{sizeId}/update")
	public ResponseEntity<ApiResponse> updateSize(@RequestBody UpdateSizeRequest request, @PathVariable Long sizeId){
		try {
			Size size = sizeService.updateSize(request, sizeId);
			return ResponseEntity.ok(new ApiResponse("Success!", size));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Error!", null));
		}
	}
	
	@DeleteMapping("/size/{sizeId}/delete")
	public ResponseEntity<ApiResponse> deleteSizeById(@PathVariable Long sizeId){
		try {
			sizeService.deleteSizeById(sizeId);
			return ResponseEntity.ok(new ApiResponse("Success!", null));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse> getAllSizes(){
		try {
			List<Size> sizes = sizeService.getAllSizes();
			return ResponseEntity.ok(new ApiResponse("Success!", sizes));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
}
