package kodlamaio.nortwind.business.concretes;

import kodlamaio.nortwind.business.abstracts.UserService;
import kodlamaio.nortwind.core.dataAccess.UserDao;
import kodlamaio.nortwind.core.entities.User;
import kodlamaio.nortwind.core.utilities.results.DataResult;
import kodlamaio.nortwind.core.utilities.results.Result;
import kodlamaio.nortwind.core.utilities.results.SuccessDataResult;
import kodlamaio.nortwind.core.utilities.results.SuccessResult;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {
    private UserDao userDao;
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public Result add(User user) {
        this.userDao.save(user);
        return new SuccessResult("Kullanıcı eklendi");
    }
    @Override
    public DataResult<User> findByEmail(String email) {
        return new SuccessDataResult<User>(this.userDao.findByEmail(email),"Kullanıcı bulundu");
    }
}
