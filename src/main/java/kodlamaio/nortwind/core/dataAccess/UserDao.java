package kodlamaio.nortwind.core.dataAccess;

import kodlamaio.nortwind.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    @Query("From User Where email=:email and password=:password")
    User getByUserControl(String email, String password);
}
