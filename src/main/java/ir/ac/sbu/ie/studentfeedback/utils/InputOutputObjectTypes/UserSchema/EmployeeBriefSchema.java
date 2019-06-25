package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.UserSchema;

import ir.ac.sbu.ie.studentfeedback.Entities.Employee;
import lombok.Data;

@Data
public class EmployeeBriefSchema {
    private Long id;
    private String fullName;
    private String jobTitle;

    public EmployeeBriefSchema(Employee e) {
        this.id = e.getId();
        this.fullName = e.getFirstName() + " " + e.getLastName();
        this.jobTitle = e.getJobTitle();
    }
}
