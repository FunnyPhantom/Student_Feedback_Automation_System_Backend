package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema;

import lombok.Data;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

@Data
@ToString
public class UserLoginInput {
    private String username;
    private String password;

    public static boolean isBlankEntry(UserLoginInput input) {
        return input == null
                || Strings.isBlank(input.getUsername())
                || Strings.isBlank(input.getPassword());
    }
}
