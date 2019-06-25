package ir.ac.sbu.ie.studentfeedback.Dao;

import ir.ac.sbu.ie.studentfeedback.Entities.Case;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CaseDao extends PagingAndSortingRepository<Case, Long> {
}
