package ir.ac.sbu.ie.studentfeedback.Entities;

import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.StudentInput;
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

    public Student(){
        super();
    }

    public static Student buildFromStudentInput(StudentInput studentInput) {
        return new Student(studentInput.getFirstName(), studentInput.getLastName(), studentInput.getUsername(), studentInput.getPassword(), studentInput.getStudentId());
    }

}
