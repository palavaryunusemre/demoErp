package kodlamaio.nortwind.business.concretes;

import kodlamaio.nortwind.business.abstracts.UserService;
import kodlamaio.nortwind.core.dataAccess.UserDao;
import kodlamaio.nortwind.core.entities.User;
import kodlamaio.nortwind.core.utilities.results.DataResult;
import kodlamaio.nortwind.core.utilities.results.Result;
import kodlamaio.nortwind.core.utilities.results.SuccessDataResult;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {
    private UserDao userDao;
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public Result add(User user) {
        try {
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
    public DataResult<User> getByUserControl(String email, String password) {
        User user = this.userDao.getByUserControl(email, password);
        if (user != null) {
            // Kullanıcı bulunduysa
            return new DataResult<>(user, true, "Kullanıcı bulundu");
        } else {
            // Kullanıcı bulunamadıysa
            return new DataResult<>(null, false, "Kullanıcı bulunamadı");
        }
    }
}