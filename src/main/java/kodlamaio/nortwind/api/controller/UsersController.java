package kodlamaio.nortwind.api.controller;

import jakarta.validation.Valid;
import kodlamaio.nortwind.business.abstracts.UserService;
import kodlamaio.nortwind.core.entities.User;
import kodlamaio.nortwind.core.utilities.results.DataResult;
import kodlamaio.nortwind.core.utilities.results.ErrorDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/users")
@CrossOrigin
public class UsersController {
    private UserService userService;

    private PasswordEncoder passwordEncoder;
    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    @PostMapping(value = "/add")
    public ResponseEntity<?> add(@Valid @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(this.userService.add(user));
    }
    @GetMapping("/getByUserControl")
    public DataResult<User> getByUserControl(@RequestParam String email, @RequestParam String password) {
        return this.userService.getByUserControl(email,password);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
        Map<String,String> validationErrors = new HashMap<String,String>();
        for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors,"Format error.");
        return errors;
    }
}
