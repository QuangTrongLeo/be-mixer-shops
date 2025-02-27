package mixer_shops.mixer.service.user;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import mixer_shops.mixer.dto.UserDto;
import mixer_shops.mixer.exceptions.ResourcesException;
import mixer_shops.mixer.model.User;
import mixer_shops.mixer.repository.UserRepository;
import mixer_shops.mixer.request.AddUserRequest;
import mixer_shops.mixer.request.UpdateUserRequest;

@Service
public class UserService implements IUserService{
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	
	public UserService(UserRepository userRepository, ModelMapper modelMapper) {
		super();
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDto getUserById(Long userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourcesException("User not found!"));
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto createUser(AddUserRequest request) {
		// TODO Auto-generated method stub
		User existingUser = userRepository.findByEmail(request.getEmail());
		if (existingUser != null) {
			throw new ResourcesException("Email already exists!");
		}
		
		User user = new User();
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		
		userRepository.save(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(UpdateUserRequest request, Long userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourcesException("User not found!"));
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		userRepository.save(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		userRepository.findById(userId).ifPresentOrElse(userRepository::delete,
				() -> { throw new ResourcesException("User not found!");});
	}

}
