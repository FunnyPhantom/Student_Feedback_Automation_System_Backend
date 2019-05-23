package ir.ac.sbu.ie.studentfeedback.Services;

import ir.ac.sbu.ie.studentfeedback.Entities.User;
import ir.ac.sbu.ie.studentfeedback.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Map<Long, User> getAllUsers() {
        Map<Long, User> userMap = new HashMap<>();
        userRepository.findAll().forEach(user -> userMap.put(user.getId(), user));
        return userMap;
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User editUser(Long id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    public ServiceStatus deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ServiceStatus.FAILED;
        }
        return ServiceStatus.SUCCESS;
    }
}

