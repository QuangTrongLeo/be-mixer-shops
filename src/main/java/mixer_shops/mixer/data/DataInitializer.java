package mixer_shops.mixer.data;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import mixer_shops.mixer.model.User;
import mixer_shops.mixer.repository.UserRepository;

@Component
public class DataInitializer implements ApplicationListener<ApplicationEvent>{
	private final UserRepository userRepository;

	public DataInitializer(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		createDefaultUserIfNotExits();
	}
	
	private void createDefaultUserIfNotExits() {
		for (int i = 1; i <= 3; i++) {
			String defaultEmail = "user00" + i + "@gmail.com";
			if (userRepository.existsByEmail(defaultEmail)) {
				continue;
			}
			User user = new User();
			user.setFirstName("The User");
			user.setLastName("User" + i);
			user.setEmail(defaultEmail);
			user.setPassword("12345678");
			userRepository.save(user);
			System.out.println("Default vet user " + i + " created successfully.");
		}
	}
}
