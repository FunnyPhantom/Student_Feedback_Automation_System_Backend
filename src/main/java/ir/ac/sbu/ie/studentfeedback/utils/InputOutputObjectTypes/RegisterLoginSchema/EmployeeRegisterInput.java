package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class EmployeeRegisterInput extends UserRegisterInput {
    protected String jobTitle;


    public static boolean isBlankEntry(EmployeeRegisterInput employeeInput) {
        return UserRegisterInput.isBlankEntry(employeeInput) ||
                Strings.isBlank(employeeInput.getJobTitle());
    }

}
