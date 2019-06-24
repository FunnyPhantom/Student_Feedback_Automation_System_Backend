package ir.ac.sbu.ie.studentfeedback.Entities;

import ir.ac.sbu.ie.studentfeedback.Entities.util.UserRole;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.EmployeeRegisterInput;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@ToString
@Entity
public class Employee extends User {
    @NotNull
    @Setter
    @Getter
    protected String jobTitle;

    public Employee(@NotNull String firstName, @NotNull String lastName, @NotNull String username, @NotNull String password, @NotNull String jobTitle) {
        super(firstName, lastName, username, password);
        this.jobTitle = jobTitle;
    }

    public Employee() {
        super();
    }

    @Override
    public UserRole getUserRole() {
        return UserRole.EMPLOYEE;
    }

    public static Employee buildFromEmployeeInput(EmployeeRegisterInput e) {
        return new Employee(e.getFirstName(), e.getLastName(), e.getUsername(), e.getPassword(), e.getJobTitle());
    }


}
