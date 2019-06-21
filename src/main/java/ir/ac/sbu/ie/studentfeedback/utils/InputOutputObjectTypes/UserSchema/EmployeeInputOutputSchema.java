package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.UserSchema;

import ir.ac.sbu.ie.studentfeedback.Entities.Employee;
import ir.ac.sbu.ie.studentfeedback.Entities.util.UserRoles;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeInputOutputSchema extends UserInputOutputSchema {

    private String jobTitle;

    @Override
    public UserRoles getUserRole() {
        return UserRoles.EMPLOYEE;
    }

    public EmployeeInputOutputSchema() {
    }

    public EmployeeInputOutputSchema(Employee e) {
        super(e);
        this.jobTitle = e.getJobTitle();
    }
}
