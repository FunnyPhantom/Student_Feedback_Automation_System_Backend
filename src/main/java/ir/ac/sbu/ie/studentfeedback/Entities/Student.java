package ir.ac.sbu.ie.studentfeedback.Entities;

import ir.ac.sbu.ie.studentfeedback.Entities.util.UserRole;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.StudentRegisterInput;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@ToString
@Entity
public class Student extends User {
    @NotNull
    @Setter
    @Getter
    protected String studentId;

    public Student(String firstName, String lastName, String username, String password, @NotNull String studentId) {
        super(firstName, lastName, username, password);
        this.studentId = studentId;
    }

    public Student() {
        super();
    }

    @Override
    public UserRole getUserRole() {
        return UserRole.STUDENT;
    }

    public static Student buildFromStudentInput(StudentRegisterInput studentInput) {
        return new Student(studentInput.getFirstName(), studentInput.getLastName(), studentInput.getUsername(), studentInput.getPassword(), studentInput.getStudentId());
    }

}
