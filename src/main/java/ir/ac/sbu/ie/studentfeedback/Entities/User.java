package ir.ac.sbu.ie.studentfeedback.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

@AllArgsConstructor
@Table(name = "sf_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private UserType userType;

    @OneToMany(mappedBy = "issuedUser", targetEntity = Case.class, fetch = FetchType.EAGER)
    @Getter @Setter
    private List<Case> issuedCases;

    @OneToMany(mappedBy = "inspectingUser", targetEntity = Case.class, fetch = FetchType.EAGER)
    @Getter @Setter
    private List<Case> inspectingCases;


    public User() {
        this.issuedCases = new ArrayList<>();
        this.inspectingCases = new ArrayList<>();
        this.userType = UserType.STUDENT;
    }

}
