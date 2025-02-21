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

import mixer_shops.mixer.dto.ColorDto;
import mixer_shops.mixer.model.Color;
import mixer_shops.mixer.request.AddColorRequest;
import mixer_shops.mixer.request.UpdateColorRequest;
import mixer_shops.mixer.response.ApiResponse;
import mixer_shops.mixer.service.color.IColorService;

@RestController
@RequestMapping("${api.prefix}/colors")
public class ColorController {
	private IColorService colorService;

	public ColorController(IColorService colorService) {
		super();
		this.colorService = colorService;
	}
	
	@GetMapping("/color/{colorId}")
	public ResponseEntity<ApiResponse> getColorById(@PathVariable Long colorId){
		try {
			Color color = colorService.getColorById(colorId);
			return ResponseEntity.ok(new ApiResponse("Success!", color));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error!", null));
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addColor(@RequestBody AddColorRequest request, @RequestParam Long productId){
		try {
			ColorDto colorDto = colorService.addColor(request, productId);
			return ResponseEntity.ok(new ApiResponse("Success!", colorDto));
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Error!", null));
		}
	}
	
	@PutMapping("/color/{colorId}/update")
	public ResponseEntity<ApiResponse> updateColor(@RequestBody UpdateColorRequest request, @PathVariable Long colorId){
		try {
			Color color = colorService.updateColor(request, colorId);
			return ResponseEntity.ok(new ApiResponse("Success!", color));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@DeleteMapping("/color/{colorId}/delete")
	public ResponseEntity<ApiResponse> deleteColorById(@PathVariable Long colorId){
		try {
			colorService.deleteColorById(colorId);
			return ResponseEntity.ok(new ApiResponse("Success!", null));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse> getAllColors(){
		try {
			List<Color> colors = colorService.getAllColors();
			return ResponseEntity.ok(new ApiResponse("Success!", colors));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
}
