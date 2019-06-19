package ir.ac.sbu.ie.studentfeedback.Services;

import ir.ac.sbu.ie.studentfeedback.Entities.User;
import ir.ac.sbu.ie.studentfeedback.Entities.Util.UserType;
import ir.ac.sbu.ie.studentfeedback.Repositories.UserRepository;
import ir.ac.sbu.ie.studentfeedback.Services.util.AuthStatus;
import ir.ac.sbu.ie.studentfeedback.Services.util.AuthValidator;
import ir.ac.sbu.ie.studentfeedback.Services.util.ServiceStatus;
import ir.ac.sbu.ie.studentfeedback.Types.LoginInputType;
import ir.ac.sbu.ie.studentfeedback.Types.RegisterInputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static ir.ac.sbu.ie.studentfeedback.Services.util.ServiceResponseMapKeys.*;

@Service
public class AuthService {




    private final AuthValidator authValidator;
    private UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository, AuthValidator authValidator) {
        this.userRepository = userRepository;
        this.authValidator = authValidator;
    }

    /**
     * @param registerEntry: register entry captured and provided by the rest controller
     * @return a map which contains:
     * KEY_SERVICE_STATUS key, which indicate Success or Failure.
     * in case of Failure, the map also contains a key named
     * KEY_AUTH_STATUS, which indicates why procedure failed.
     */
    public Map<String, Object> register(RegisterInputType registerEntry) {
        Map<String, Object> map = new HashMap<>();
        // validating input
        if (authValidator.validateRegisterEntry(registerEntry) == AuthStatus.VALID) {
            // validating uniqueness
            if (userRepository.findByUsername(registerEntry.getUsername()).isPresent()) {
                map.put(KEY_SERVICE_STATUS, ServiceStatus.FAILED);
                map.put(KEY_AUTH_STATUS, AuthStatus.USERNAME_EXIST);
            } else if (userRepository.findBySid(registerEntry.getSid()).isPresent()) {
                map.put(KEY_SERVICE_STATUS, ServiceStatus.FAILED);
                map.put(KEY_AUTH_STATUS, AuthStatus.SID_EXIST);
            }
            // saving the user
            else  {
                User u = new User(registerEntry.getUsername(), registerEntry.getPassword(), UserType.STUDENT, 1);
                u.setSid(registerEntry.getSid());
                userRepository.save(u);
                map.put(KEY_SERVICE_STATUS, ServiceStatus.SUCCESS);

            }
        } else {
            map.put(KEY_SERVICE_STATUS, ServiceStatus.FAILED);
            map.put(KEY_AUTH_STATUS, authValidator.validateRegisterEntry(registerEntry));
        }
        return map;
    }

    public Map<String, Object> login(LoginInputType loginEntry) {
        Map<String, Object> map = new HashMap<>();
        // validating input
        if (authValidator.validateLoginEntry(loginEntry) == AuthStatus.VALID) {
            User u = userRepository.findByUsername(loginEntry.getUsername()).orElse(null);
            // validating user existence
            if (u == null) {
                map.put(KEY_SERVICE_STATUS, ServiceStatus.FAILED);
                map.put(KEY_AUTH_STATUS, AuthStatus.USERNAME_NOT_EXIST);
            } else {
                // validating same password // todoOptional: save hash as password. not the password itself.
                if (!u.getPassword().equals(loginEntry.getPassword())) {
                    map.put(KEY_SERVICE_STATUS, ServiceStatus.FAILED);
                    map.put(KEY_AUTH_STATUS, AuthStatus.PASSWORD_NOT_MATCH);
                } else {
                    // setting the token if the token does not exist.
                    if (u.getToken() == null) {
                        //todo: generate token and set token and persist user with the token.
                        u.setToken("Bearer " + u.getUsername().hashCode());
                        userRepository.save(u);
                    }
                    map.put(KEY_SERVICE_STATUS, ServiceStatus.SUCCESS);
                    map.put(KEY_USER_TOKEN, u.getToken());
                }
            }
        } else {
            map.put(KEY_SERVICE_STATUS, ServiceStatus.FAILED);
            map.put(KEY_AUTH_STATUS, authValidator.validateLoginEntry(loginEntry));
        }
        return map;
    }

    public Map<String, Object> getUser(String token) {
        Map<String, Object> map = new HashMap<>();
        if (token != null) {
            User u = userRepository.findByToken(token).orElse(null);
            if (u == null) {
                map.put(KEY_SERVICE_STATUS, ServiceStatus.FAILED);
                map.put(KEY_AUTH_STATUS, AuthStatus.TOKEN_NOT_FOUND);
            } else {
                    map.put(KEY_SERVICE_STATUS, ServiceStatus.SUCCESS);
                    map.put(KEY_USER_TYPE, u.getUserType());
                    map.put(KEY_SAVED_USER, u);
            }
        } else {
            map.put(KEY_SERVICE_STATUS, ServiceStatus.FAILED);
            map.put(KEY_AUTH_STATUS, AuthStatus.TOKEN_EMPTY);
        }
        return map;
    }
}
