package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class EmployeeInput extends UserInput {
    protected String jobTitle;


    public static boolean isBlankEntry(EmployeeInput employeeInput) {
        return UserInput.isBlankEntry(employeeInput) ||
                Strings.isBlank(employeeInput.getJobTitle());
    }

}
