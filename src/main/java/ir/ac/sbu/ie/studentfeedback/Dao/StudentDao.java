package ir.ac.sbu.ie.studentfeedback.Dao;

import ir.ac.sbu.ie.studentfeedback.Entities.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface StudentDao extends PagingAndSortingRepository<Student, Long> {
    Optional<Student> findByUsername(String username);

    Optional<Student> findByStudentId(String sid);
}
