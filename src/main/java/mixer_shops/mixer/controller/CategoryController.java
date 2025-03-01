package mixer_shops.mixer.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mixer_shops.mixer.dto.CategoryDto;
import mixer_shops.mixer.request.AddCategoryRequest;
import mixer_shops.mixer.request.UpdateCategoryRequest;
import mixer_shops.mixer.response.ApiResponse;
import mixer_shops.mixer.service.category.ICategoryService;

@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {
	private ICategoryService categoryService;

	public CategoryController(ICategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	@CrossOrigin(origins = "${api.host}")
	@GetMapping("/all")
	public ResponseEntity<ApiResponse> getAllCategories(){
		try {
			List<CategoryDto> categoryDaos = categoryService.getAllCategories();
			return ResponseEntity.ok(new ApiResponse("Success!", categoryDaos));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addCategory(@RequestBody AddCategoryRequest request){
		try {
			CategoryDto newCategoryDto = categoryService.addCategory(request);
			return ResponseEntity.ok(new ApiResponse("Success!", newCategoryDto)); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@PutMapping("/category/{categoryId}/update")
	public ResponseEntity<ApiResponse> updateCategory(@RequestBody UpdateCategoryRequest request, @PathVariable Long categoryId){
		try {
			CategoryDto updateCategoryDto = categoryService.updateCategory(request, categoryId);
			return ResponseEntity.ok(new ApiResponse("Success!", updateCategoryDto)); 
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		} 
	}
	
	@CrossOrigin(origins = "${api.host}")
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long categoryId){
		try {
			CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
			return ResponseEntity.ok(new ApiResponse("Success!", categoryDto)); 
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@CrossOrigin(origins = "${api.host}")
	@GetMapping("/{categoryName}/category")
	public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String categoryName){
		try {
			CategoryDto categoryDto = categoryService.getCategoryByName(categoryName);
			return ResponseEntity.ok(new ApiResponse("Success!", categoryDto)); 
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@DeleteMapping("/category/{categoryId}/delete")
	public ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable Long categoryId){
		try {
			categoryService.deleteCategoryById(categoryId);
			return ResponseEntity.ok(new ApiResponse("Success!", null)); 
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
}
