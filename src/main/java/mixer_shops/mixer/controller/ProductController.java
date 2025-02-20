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

import mixer_shops.mixer.model.Product;
import mixer_shops.mixer.request.AddProductRequest;
import mixer_shops.mixer.request.UpdateProductRequest;
import mixer_shops.mixer.response.ApiResponse;
import mixer_shops.mixer.service.product.IProductService;

@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
	private final IProductService productService;

	public ProductController(IProductService productService) {
		super();
		this.productService = productService;
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId){
		try {
			Product product = productService.getProductById(productId);
			return ResponseEntity.ok(new ApiResponse("Success!", product));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest request){
		try {
			Product product = productService.addProduct(request);
			return ResponseEntity.ok(new ApiResponse("Success!", product));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@PutMapping("/product/{productId}/update")
	public ResponseEntity<ApiResponse> updateProduct(@RequestBody UpdateProductRequest request, @PathVariable Long productId){
		try {
			Product product = productService.updateProduct(request, productId);
			return ResponseEntity.ok(new ApiResponse("Success!", product));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@DeleteMapping("/product/{productId}/delete")
	public ResponseEntity<ApiResponse> deleteProductById(@PathVariable Long productId){
		try {
			productService.deleteProductById(productId);
			return ResponseEntity.ok(new ApiResponse("Success!", null));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error!", null));
		}
	}
	
	@GetMapping("/all")
	public ResponseEntity<ApiResponse> getAllProducts(){
		try {
			List<Product> products = productService.getAllProducts();
			return ResponseEntity.ok(new ApiResponse("Success!", products));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@GetMapping("/by/category/{categoryName}/products")
	public ResponseEntity<ApiResponse> getProductsByCategoryId(@PathVariable String categoryName){
		try {
			List<Product> products = productService.getProductsByCategory(categoryName);
			return ResponseEntity.ok(new ApiResponse("Success!", products));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@GetMapping("/by/{name}/product")
	public ResponseEntity<ApiResponse> getProductsByName(@PathVariable String name){
		try {
			List<Product> products = productService.getProductsByName(name);
			return ResponseEntity.ok(new ApiResponse("Success!", products));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
}
