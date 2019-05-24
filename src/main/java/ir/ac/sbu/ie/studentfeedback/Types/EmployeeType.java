package ir.ac.sbu.ie.studentfeedback.Types;

import ir.ac.sbu.ie.studentfeedback.Entities.Case;
import ir.ac.sbu.ie.studentfeedback.Entities.Util.UserType;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeType {
    private Long id;
    private String username;
    private UserType userType;
    private List<Case> inspectingCases;
}
