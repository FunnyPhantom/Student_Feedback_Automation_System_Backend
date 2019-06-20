package ir.ac.sbu.ie.studentfeedback.Entities;

import ir.ac.sbu.ie.studentfeedback.Entities.util.UserValidationStatus;
import lombok.ToString;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@ToString
@Entity
public class Admin extends User{
    public Admin(@NotNull String firstName, @NotNull String lastName, @NotNull String username, @NotNull String password) {
        super(firstName, lastName, username, password);
        this.validationStatus = UserValidationStatus.VALIDATED;
    }
    public Admin() {
        super();
        this.validationStatus = UserValidationStatus.VALIDATED;
    }
}
