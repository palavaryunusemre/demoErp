package kodlamaio.nortwind.core.dataAccess;

import kodlamaio.nortwind.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
