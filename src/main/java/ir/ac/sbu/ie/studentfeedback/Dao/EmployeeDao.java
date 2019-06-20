package ir.ac.sbu.ie.studentfeedback.Dao;

import ir.ac.sbu.ie.studentfeedback.Entities.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface EmployeeDao extends PagingAndSortingRepository<Employee, Long> {

    Optional<Employee> findByUsername(String username);
}
