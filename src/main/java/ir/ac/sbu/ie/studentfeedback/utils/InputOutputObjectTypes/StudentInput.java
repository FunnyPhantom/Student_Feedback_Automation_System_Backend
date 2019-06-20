package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StudentInput extends UserInput {
    protected String studentId;


    public static boolean isBlankEntry(StudentInput studentInput) {
        return studentInput == null || UserInput.isBlankEntry(studentInput) ||
                Strings.isBlank(studentInput.getStudentId());
    }
}
