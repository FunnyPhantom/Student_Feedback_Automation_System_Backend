package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.UserSchema;

import ir.ac.sbu.ie.studentfeedback.Entities.User;
import ir.ac.sbu.ie.studentfeedback.Entities.util.UserRole;
import ir.ac.sbu.ie.studentfeedback.Entities.util.UserValidationStatus;
import lombok.Data;

@Data
public abstract class UserInputOutputSchema {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private UserValidationStatus validationStatus;

    public UserInputOutputSchema() {
    }

    public UserInputOutputSchema(User u) {
        this.id = u.getId();
        this.username = u.getUsername();
        this.firstName = u.getFirstName();
        this.lastName = u.getLastName();
        this.validationStatus = u.getValidationStatus();
    }

    public abstract UserRole getUserRole();

}
