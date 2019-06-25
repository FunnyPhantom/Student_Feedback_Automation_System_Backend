package ir.ac.sbu.ie.studentfeedback.Entities;

import ir.ac.sbu.ie.studentfeedback.Entities.util.UserRole;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.EmployeeRegisterInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@ToString
@Entity
public class Employee extends User {
    public final static Employee EMPTY_EMPLOYEE = new Employee("empty", "employee", "empty_reserved", "1234", "empty employee");
    @NotNull
    @Setter
    @Getter
    protected String jobTitle;

    @Setter
    @Getter
    @OneToMany(mappedBy = "responsibleEmployee")
    private List<Case> responsibleCases;

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
