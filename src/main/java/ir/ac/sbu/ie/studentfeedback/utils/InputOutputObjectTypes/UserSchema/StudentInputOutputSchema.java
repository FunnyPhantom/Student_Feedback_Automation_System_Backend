package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.UserSchema;

import ir.ac.sbu.ie.studentfeedback.Entities.Student;
import ir.ac.sbu.ie.studentfeedback.Entities.util.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentInputOutputSchema extends UserInputOutputSchema {

    private String studentId;

    @Override
    public UserRole getUserRole() {
        return UserRole.STUDENT;
    }

    public StudentInputOutputSchema() {
    }

    public StudentInputOutputSchema(Student s) {
        super(s);
        this.studentId = getStudentId();
    }

}
