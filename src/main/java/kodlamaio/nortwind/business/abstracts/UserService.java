package kodlamaio.nortwind.business.abstracts;

import kodlamaio.nortwind.core.entities.User;
import kodlamaio.nortwind.core.utilities.results.DataResult;
import kodlamaio.nortwind.core.utilities.results.Result;
import kodlamaio.nortwind.entities.dtos.UserDto;

public interface UserService {
    Result add(User user);
    DataResult<User> findByEmail(String email);
    DataResult<UserDto> getByUserControl(String email, String password);
}
