package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.UserSchema;

import ir.ac.sbu.ie.studentfeedback.Entities.Admin;
import ir.ac.sbu.ie.studentfeedback.Entities.util.UserRole;

public class AdminInputOutputSchema extends UserInputOutputSchema {
    @Override
    public UserRole getUserRole() {
        return UserRole.ADMIN;
    }

    public AdminInputOutputSchema() {
    }

    public AdminInputOutputSchema(Admin a) {
        super(a);
    }
}
