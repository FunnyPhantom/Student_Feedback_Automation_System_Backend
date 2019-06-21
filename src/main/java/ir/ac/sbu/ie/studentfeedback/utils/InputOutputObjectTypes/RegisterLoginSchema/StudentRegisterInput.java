package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StudentRegisterInput extends UserRegisterInput {
    protected String studentId;


    public static boolean isBlankEntry(StudentRegisterInput studentInput) {
        return studentInput == null || UserRegisterInput.isBlankEntry(studentInput) ||
                Strings.isBlank(studentInput.getStudentId());
    }
}
