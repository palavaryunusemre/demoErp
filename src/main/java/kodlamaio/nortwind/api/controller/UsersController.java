package kodlamaio.nortwind.api.controller;

import jakarta.validation.Valid;
import kodlamaio.nortwind.business.abstracts.UserService;
import kodlamaio.nortwind.core.utilities.results.DataResult;
import kodlamaio.nortwind.core.utilities.results.ErrorDataResult;
import kodlamaio.nortwind.entities.concretes.AdminUser;
import kodlamaio.nortwind.entities.dtos.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/users")
@CrossOrigin
@RequiredArgsConstructor
public class UsersController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/add")
    public ResponseEntity<?> add(@Valid @RequestBody AdminUser user) {
        return ResponseEntity.ok(this.userService.add(user));
    }
    @PostMapping("/login")
    public DataResult<String> getByUserControl(@Valid @RequestBody Map<String, String> userCredentials) {
        String email = userCredentials.get("email");
        String password = userCredentials.get("password");
        return this.userService.getByUserControl(email, password);
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
