package ir.ac.sbu.ie.studentfeedback.Entities;

import ir.ac.sbu.ie.studentfeedback.Entities.util.UserRole;
import ir.ac.sbu.ie.studentfeedback.Entities.util.UserValidationStatus;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.StudentRegisterInput;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@ToString
@Entity
public class Student extends User {
    @NotNull
    @Setter
    @Getter
    protected String studentId;

    @Setter
    @Getter
    @OneToMany(mappedBy = "issuingStudent")
    private List<Case> issuedCases;

    public Student(String firstName, String lastName, String username, String password, @NotNull String studentId) {
        super(firstName, lastName, username, password);
        this.studentId = studentId;
        this.setValidationStatus(UserValidationStatus.VALIDATED);
    }

    public Student() {
        super();
        this.setValidationStatus(UserValidationStatus.VALIDATED);

    }

    @Override
    public UserRole getUserRole() {
        return UserRole.STUDENT;
    }

    public static Student buildFromStudentInput(StudentRegisterInput studentInput) {
        return new Student(studentInput.getFirstName(), studentInput.getLastName(), studentInput.getUsername(), studentInput.getPassword(), studentInput.getStudentId());
    }

}
