package ir.ac.sbu.ie.studentfeedback.Dao;

import ir.ac.sbu.ie.studentfeedback.Entities.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminDao extends CrudRepository<Admin, Long> {
}
