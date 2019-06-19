package ir.ac.sbu.ie.studentfeedback.Dao;

import ir.ac.sbu.ie.studentfeedback.Entities.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeDao extends PagingAndSortingRepository<Employee, Long> {
}
