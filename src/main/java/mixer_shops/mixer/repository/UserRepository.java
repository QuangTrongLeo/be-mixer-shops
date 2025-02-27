package mixer_shops.mixer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mixer_shops.mixer.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);

}
