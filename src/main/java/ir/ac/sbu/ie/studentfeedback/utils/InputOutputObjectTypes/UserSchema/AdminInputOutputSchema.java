package ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.UserSchema;

import ir.ac.sbu.ie.studentfeedback.Entities.Admin;
import ir.ac.sbu.ie.studentfeedback.Entities.util.UserRoles;

public class AdminInputOutputSchema extends UserInputOutputSchema {
    @Override
    public UserRoles getUserRole() {
        return UserRoles.ADMIN;
    }

    public AdminInputOutputSchema() {
    }

    public AdminInputOutputSchema(Admin a) {
        super(a);
    }
}
