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
import org.springframework.web.bind.annotation.RestController;

import mixer_shops.mixer.model.Category;
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
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse> getAllCategories(){
		try {
			List<Category> categories = categoryService.getAllCategories();
			return ResponseEntity.ok(new ApiResponse("Success!", categories));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category){
		try {
			Category newCategory = categoryService.addCategory(category);
			return ResponseEntity.ok(new ApiResponse("Success!", newCategory)); 
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@PutMapping("/category/{categoryId}/update")
	public ResponseEntity<ApiResponse> updateCategory(@RequestBody Category category, @PathVariable Long categoryId){
		try {
			Category updateCategory = categoryService.updateCategory(category, categoryId);
			return ResponseEntity.ok(new ApiResponse("Success!", updateCategory)); 
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		} 
	}
	
	@GetMapping("/{categoryId}/category")
	public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long categoryId){
		try {
			Category category = categoryService.getCategoryById(categoryId);
			return ResponseEntity.ok(new ApiResponse("Success!", category)); 
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@GetMapping("/{name}/category")
	public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name){
		try {
			Category category = categoryService.getCategoryByName(name);
			return ResponseEntity.ok(new ApiResponse("Success!", category)); 
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
