package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

@Data
@EqualsAndHashCode
@ToString
public abstract class UserInput {
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String password;

    public static boolean isBlankEntry(UserInput userInput) {
        return Strings.isBlank(userInput.getFirstName()) ||
                Strings.isBlank(userInput.getLastName()) ||
                Strings.isBlank(userInput.getUsername()) ||
                Strings.isBlank(userInput.getPassword());
    }
}
