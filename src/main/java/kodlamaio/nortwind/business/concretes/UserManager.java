package kodlamaio.nortwind.business.concretes;

import kodlamaio.nortwind.business.abstracts.UserService;
import kodlamaio.nortwind.core.dataAccess.UserDao;
import kodlamaio.nortwind.core.entities.User;
import kodlamaio.nortwind.core.utilities.results.DataResult;
import kodlamaio.nortwind.core.utilities.results.Result;
import kodlamaio.nortwind.core.utilities.results.SuccessDataResult;
import kodlamaio.nortwind.entities.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserManager(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Result add(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            this.userDao.save(user);
            return new Result(true,"User has been created.");
        }catch (DataIntegrityViolationException e){
            return new Result(false,"Email already exists");
        }

    }
    @Override
    public DataResult<User> findByEmail(String email) {
        return new SuccessDataResult<User>(this.userDao.findByEmail(email),"Kullanıcı bulundu");
    }

    @Override
    public DataResult<UserDto> getByUserControl(String email, String password) {
        User user  = this.userDao.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getUserName());
            return new DataResult<UserDto>(userDto, true,"Login successful.");
        } else {
            return new DataResult<UserDto>(null,false,"Invalid email or password.");
        }

    }
}
