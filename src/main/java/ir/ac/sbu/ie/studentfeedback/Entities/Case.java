package ir.ac.sbu.ie.studentfeedback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.ac.sbu.ie.studentfeedback.Entities.util.CaseStatus;
import ir.ac.sbu.ie.studentfeedback.Entities.util.CaseType;
import ir.ac.sbu.ie.studentfeedback.Entities.util.Satisfaction;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String title;
    @Setter
    @Getter
    private String description;

    @Setter
    @Getter
    private CaseType caseType;

    @Setter
    @Getter
    private CaseStatus caseStatus;

    @Setter
    @Getter
    private Date issueDate;


    @Setter
    @Getter
    @JsonIgnore
    @ManyToOne
    private Student issuingStudent;

    @Setter
    @Getter
    @JsonIgnore
    @ManyToOne
    private Employee responsibleEmployee;

    @Setter
    @Getter
    private Satisfaction studentSatisfaction;


    public Case() {
        this.issueDate = new Date();
        this.studentSatisfaction = Satisfaction.UNKNOWN;
    }

    public Case(String title, String description, CaseType caseType, CaseStatus caseStatus, Student issuingStudent, Employee responsibleEmployee) {
        this();
        this.title = title;
        this.description = description;
        this.caseType = caseType;
        this.caseStatus = caseStatus;
        this.issuingStudent = issuingStudent;
        this.responsibleEmployee = responsibleEmployee;
    }


}
