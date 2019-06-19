package ir.ac.sbu.ie.studentfeedback.Dao;

import ir.ac.sbu.ie.studentfeedback.Entities.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentDao extends PagingAndSortingRepository<Student, Long> {
}
