package ir.ac.sbu.ie.studentfeedback.Entities;

import ir.ac.sbu.ie.studentfeedback.Entities.Util.CaseStatus;
import ir.ac.sbu.ie.studentfeedback.Entities.Util.CaseCategory;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@ToString
@Table(name = "sf_case")
public class Case implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;
    @Getter @Setter @NotNull
    private String title;
    @Getter @Setter
    private String description;
    @Getter @Setter @NotNull
    private CaseCategory caseCategory;
    @Getter @Setter @NotNull
    private CaseStatus caseStatus;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issued_user_id")
    @Setter
    private User issuingUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspecting_user_id")
    @Setter
    private User inspectingUser;

    public Case() {
        this.caseCategory = CaseCategory.SUGGESTION;
        this.caseStatus = CaseStatus.OPEN;
    }

}
