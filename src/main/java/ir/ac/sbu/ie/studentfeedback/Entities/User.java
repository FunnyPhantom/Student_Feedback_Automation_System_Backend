package ir.ac.sbu.ie.studentfeedback.Entities;

import ir.ac.sbu.ie.studentfeedback.Entities.util.UserRoles;
import ir.ac.sbu.ie.studentfeedback.Entities.util.UserValidationStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@ToString
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    protected Long id;
    @NotNull
    @Setter
    @Getter
    protected String firstName;
    @NotNull
    @Setter
    @Getter
    protected String lastName;
    @NotNull
    @Setter
    @Getter
    protected String username;
    @NotNull
    @Setter
    @Getter
    protected String password;

    @NotNull
    @Setter
    @Getter
    protected UserValidationStatus validationStatus;

    @Setter
    @Getter
    protected String authorizationToken;


    public User(@NotNull String firstName, @NotNull String lastName, @NotNull String username, @NotNull String password) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public User() {
        this.validationStatus = UserValidationStatus.PENDING;
    }

    public abstract UserRoles getUserRole();

}
