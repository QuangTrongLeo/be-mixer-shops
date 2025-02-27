package mixer_shops.mixer.service.user;

import mixer_shops.mixer.dto.UserDto;
import mixer_shops.mixer.request.AddUserRequest;
import mixer_shops.mixer.request.UpdateUserRequest;

public interface IUserService {
	UserDto getUserById(Long userId);
	UserDto createUser(AddUserRequest request);
	UserDto updateUser(UpdateUserRequest request, Long userId);
	void deleteUser(Long userId);
}
