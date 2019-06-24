package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

@Data
@EqualsAndHashCode
@ToString
public abstract class UserRegisterInput {
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String password;

    public static boolean isBlankEntry(UserRegisterInput userRegisterInput) {
        return userRegisterInput == null ||
                Strings.isBlank(userRegisterInput.getFirstName()) ||
                Strings.isBlank(userRegisterInput.getLastName()) ||
                Strings.isBlank(userRegisterInput.getUsername()) ||
                Strings.isBlank(userRegisterInput.getPassword());
    }
}
