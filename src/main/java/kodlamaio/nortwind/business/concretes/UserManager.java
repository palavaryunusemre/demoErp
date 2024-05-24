package kodlamaio.nortwind.business.concretes;

import kodlamaio.nortwind.business.abstracts.UserService;
import kodlamaio.nortwind.core.dataAccess.UserDao;
import kodlamaio.nortwind.core.entities.User;
import kodlamaio.nortwind.core.enums.Role;
import kodlamaio.nortwind.core.utilities.results.DataResult;
import kodlamaio.nortwind.core.utilities.results.Result;
import kodlamaio.nortwind.core.utilities.results.SuccessDataResult;
import kodlamaio.nortwind.core.utilities.services.JwtService;
import kodlamaio.nortwind.entities.concretes.AdminUser;
import kodlamaio.nortwind.entities.dtos.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private JwtService jwtService;
    @Override
    public Result add(AdminUser user) {
        try {
            user.setPassword(this.passwordEncoder().encode(user.getPassword()));
            user.setRole(Role.ADMIN);
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
    public DataResult<UserResponseDto> getByUserControl(String email, String password) {

        AdminUser user  = (AdminUser) this.userDao.findByEmail(email);
        if (user != null && this.passwordEncoder().matches(password, user.getPassword())) {
            var token=jwtService.generateToken(user);
            UserResponseDto userDto = new UserResponseDto();
            userDto.setId(user.getId());
            userDto.setName(user.getUserName());
            userDto.setToken(token);
            return new DataResult<UserResponseDto>(userDto, true,"Login successful.");
        } else {
            return new DataResult<UserResponseDto>(null,false,"Invalid email or password.");
        }

    }

    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User userDataResult = this.userDao.findByEmail(email);
        if(userDataResult==null){
            throw  new UsernameNotFoundException("User not found with email");
        }
        User user = userDataResult;
        return user;
    }
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
