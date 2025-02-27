package mixer_shops.mixer.controller;

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

import mixer_shops.mixer.dto.UserDto;
import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.request.AddUserRequest;
import mixer_shops.mixer.request.UpdateUserRequest;
import mixer_shops.mixer.response.ApiResponse;
import mixer_shops.mixer.service.user.IUserService;

@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {
	private final IUserService userService;

	public UserController(IUserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/{userId}/user")
	public ResponseEntity<ApiResponse> getUserById(@PathVariable Long userId){
		try {
			UserDto userDto = userService.getUserById(userId);
			return ResponseEntity.ok(new ApiResponse("Success!", userDto));
		} catch (ResourcesException e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> createUser(@RequestBody AddUserRequest request){
		try {
			UserDto userDto = userService.createUser(request);
			return ResponseEntity.ok(new ApiResponse("Success!", userDto));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@PutMapping("/{userId}/update")
	public ResponseEntity<ApiResponse> updateUser(@RequestBody UpdateUserRequest request, @PathVariable Long userId){
		try {
			UserDto userDto = userService.updateUser(request, userId);
			return ResponseEntity.ok(new ApiResponse("Success!", userDto));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
	
	@DeleteMapping("/{userId}/delete")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId){
		try {
			userService.deleteUser(userId);
			return ResponseEntity.ok(new ApiResponse("Success!", null));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
		}
	}
}
