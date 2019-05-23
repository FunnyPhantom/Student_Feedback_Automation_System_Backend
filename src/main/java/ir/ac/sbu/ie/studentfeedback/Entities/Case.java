package ir.ac.sbu.ie.studentfeedback.Entities;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sf_case")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;
    @Getter @Setter @NonNull
    private String title;
    @Getter @Setter
    private String description;
    @Getter @Setter @NonNull
    private CaseType caseType;


    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "issuedUserId")
    User issuedUser;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "inspectingUserId")
    User inspectingUser;
}
