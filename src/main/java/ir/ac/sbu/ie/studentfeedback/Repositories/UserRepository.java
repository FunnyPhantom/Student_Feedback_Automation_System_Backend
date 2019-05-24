package ir.ac.sbu.ie.studentfeedback.Repositories;

import ir.ac.sbu.ie.studentfeedback.Entities.User;
import ir.ac.sbu.ie.studentfeedback.Entities.Util.UserType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByToken(String token);
    Optional<User> findBySid(String sid);
    Optional<User> findByUsername(String username);
    Iterable<User> findAllByUserType(UserType userType);
    Iterable<User> findAllByValidated(Boolean validated);
}
