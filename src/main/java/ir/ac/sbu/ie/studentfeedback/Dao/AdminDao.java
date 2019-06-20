package ir.ac.sbu.ie.studentfeedback.Dao;

import ir.ac.sbu.ie.studentfeedback.Entities.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminDao extends CrudRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
}
