package ir.ac.sbu.ie.studentfeedback.Repositories;

import ir.ac.sbu.ie.studentfeedback.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
