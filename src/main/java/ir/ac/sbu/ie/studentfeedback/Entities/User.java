package ir.ac.sbu.ie.studentfeedback.Entities;

import ir.ac.sbu.ie.studentfeedback.Entities.Util.UserType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "sf_users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Getter @Setter @NonNull
    private String username;
    @Getter @Setter @NonNull
    private String password;
    @Getter @Setter
    private String sid;
    @Getter @Setter @NonNull
    private UserType userType;
    @Getter @Setter
    private String token;

    @Getter @Setter @NonNull
    private Integer validated;

    @OneToMany(mappedBy = "issuingUser")
    @Getter @Setter
    private List<Case> issuedCases;

    @OneToMany(mappedBy = "inspectingUser", fetch = FetchType.LAZY)
    @Getter @Setter
    private List<Case> inspectingCases;


    public User() {
        this.issuedCases = new ArrayList<>();
        this.inspectingCases = new ArrayList<>();
        this.userType = UserType.STUDENT;
        this.validated = 1;
    }

}
