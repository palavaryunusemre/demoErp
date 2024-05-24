package kodlamaio.nortwind.business.abstracts;

import kodlamaio.nortwind.core.entities.User;
import kodlamaio.nortwind.core.utilities.results.DataResult;
import kodlamaio.nortwind.core.utilities.results.Result;
import kodlamaio.nortwind.entities.concretes.AdminUser;
import kodlamaio.nortwind.entities.dtos.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    Result add(AdminUser user);
    DataResult<User> findByEmail(String email);
    DataResult<UserResponseDto> getByUserControl(String email, String password);
    UserDetails loadUserByEmail(String email);
}
