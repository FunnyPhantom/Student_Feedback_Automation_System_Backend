package ir.ac.sbu.ie.studentfeedback.Repositories;

import ir.ac.sbu.ie.studentfeedback.Entities.Case;
import org.springframework.data.repository.CrudRepository;

public interface CasesRepository extends CrudRepository<Case, Long> {
}
