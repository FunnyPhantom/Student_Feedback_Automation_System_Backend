package ir.ac.sbu.ie.studentfeedback.Repositories;

import ir.ac.sbu.ie.studentfeedback.Entities.Case;
import org.springframework.data.repository.CrudRepository;

public interface CaseRepository extends CrudRepository<Case, Long> {
    Iterable<Case> findAllByIssuingUser_Id(Long issuingUserId);
    Iterable<Case> findAllByInspectingUser_Id(Long inspectingUserId);
}
