package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public abstract class UserInput {
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String password;
}
