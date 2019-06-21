package ir.ac.sbu.ie.studentfeedback.Entities;

import ir.ac.sbu.ie.studentfeedback.Entities.util.UserRoles;
import ir.ac.sbu.ie.studentfeedback.Entities.util.UserValidationStatus;
import ir.ac.sbu.ie.studentfeedback.utils.InputOutputObjectTypes.RegisterLoginSchema.AdminRegisterInput;
import lombok.ToString;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@ToString
@Entity
public class Admin extends User {
    public Admin(@NotNull String firstName, @NotNull String lastName, @NotNull String username, @NotNull String password) {
        super(firstName, lastName, username, password);
        this.validationStatus = UserValidationStatus.VALIDATED;
    }

    public Admin() {
        super();
        this.validationStatus = UserValidationStatus.VALIDATED;
    }

    public static Admin buildAdminFromRegisterInput(AdminRegisterInput registerInput) {
        return new Admin(registerInput.getFirstName(), registerInput.getLastName(), registerInput.getUsername(), registerInput.getPassword());
    }

    @Override
    public UserRoles getUserRole() {
        return UserRoles.ADMIN;
    }
}
